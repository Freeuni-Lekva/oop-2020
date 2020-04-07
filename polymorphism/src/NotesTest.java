import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotesTest {

    // -------------- Ad-hoc polymorphism

    void printValues(Super[] arr) {
        for (Super s : arr) {
            // Calling getValue here is safe no matter of RT type of objects stored in arr, as compiler guarantees that
            // they will be subclasses of Super, which has getValue method.
            System.out.println(s.getValue());
        }
    }

    @Test
    void arrays() {
        Child[] childArr = new Child[5];
        for (int i = 0; i < 5; i++) {
            childArr[i] = new Child(String.valueOf(i));
        }
        Super[] superArr = childArr;
        for (int i = 0; i < 5; i++) {
            assertEquals(String.valueOf(i), superArr[i].getValue());
        }
        printValues(superArr);
        // Passing Child[] array directly works because of implicit casting
        printValues(childArr);
        Child ch = (Child) superArr[0];
        // ClassCastException:
        // GrandChild gch = (GrandChild) superArr[0];
    }

    @Test
    void storeGrandchildren() {
        Super[] arr = new Child[2];
        arr[0] = new Child("foo");
        arr[1] = new GrandChild("bar");
        assertEquals("foo", arr[0].getValue());
        // Note: returns reversed value.
        assertEquals("rab", arr[1].getValue());
    }

    @Test
    void storeMismatchTypeInArray() {
        Super[] arr = new Child[5];
        arr[0] = new Child(String.valueOf(3));
        assertThrows(ArrayStoreException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                // No compilation error as CT (Compile Time) type of arr array is Super[] so theoretically it can hold
                // objects of any Super subclass. But RT (Runtime) type is Child[] as it points to Child array
                // and that can hold only Child-s or their subclasses which Sibling is not.
                // So we get run time ArrayStoreException.
                arr[1] = new Sibling();
            }
        });
    }

    @Test
    void functionReturnTypes() {
        Printer p = new Printer() {
            @Override
            public GrandChild print(Super s) {  // Note: returns GrandChild instead of Super.
                GrandChild reversed = new GrandChild(s.getValue());
                System.out.print(reversed.getValue());
                return reversed;
            }
        };
        assertEquals("oof", p.print(new Child("foo")).getValue());
        assertEquals("foo", p.print(new GrandChild("foo")).getValue());
    }

//    @Test
//    void functionArgumentTypes() {
//        Printer p = new Printer() {
//            // This does not compile as in Java you can not change argument types when overriding methods.
//            // But purely theoretically speaking, type theory does not prohibit it.
//            @Override
//            public Super print(Object s) {
//                Child c = new Child(s.toString());
//                System.out.print(c.getValue());
//                return c;
//            }
//        };
//        assertEquals("foo", p.print(new Child("foo")).getValue());
//    }

    // -------------- END OF: Ad-hoc polymorphism

    // -------------- Parametric polymorphism: Generics

    @Test
    void genericTypePolymorphism() {
        // Line below does not compile as generic types in java are invariant.
        // Unlike arrays, after compilation generic types "forget" about their parameter types because of type erasure.
        // So RT type of List<Super> actually is List<Object> and if JVM allowed lines like below to be valid, it would
        // not have any way of checking if objects of correct types are being added to the list. Unlike arrays were we
        // get ArrayStoreException in case of type incompatibility. So language committee just decided to make generic
        // types invariant.
        // -- List<Super> l = new ArrayList<Child>();
        // But line below is OK as List<Supert> turns into List<Object> or List for simplicity and ArrayList<Super>
        // turns into ArrayList, which is subclass of List.x
        List<Super> list = new ArrayList<Super>();
    }

    @Test
    void genericTypeArrays() {
        // Line below does not compile as array types are covariant and generic types invariant.
        // So they are not compatible and can not be mixed.
        // new ArrayList<Child>[2];
    }

    // -------------- END OF: Parametric polymorphism: Generics

    // -------------- Generic Subtyping

    void printValues(List<Super> l) {
        for (Super s : l) {
            System.out.println(s.getValue());
        }
    }

    void printValuesWildcard(List<? extends Super> l) {
        for (Super s : l) {
            System.out.println(s.getValue());
        }
        // But <? extends Super> creates "read-only" objects and line below does not compile.
        // Read-only because RT type of l is still List (type erasure) and JVM has no way of enforcing type compatibility.
        // l.add(new Child("123"));
    }

    @Test
    void read() {
        List<Child> l = new ArrayList<>();
        l.add(new Child("foo"));
        l.add(new Child("bar"));
        // Line below does not compile as List<Child> can not be cast to List<Super> because generic types are invariant.
        // printValues(l);
        printValuesWildcard(l);  // But this is OK
    }

    void addValue(List<GrandChild> l) {
        l.add(new GrandChild("123"));
    }

    void addValuesWildCard(List<? super GrandChild> l) {
        l.add(new GrandChild("123"));
        // <? super Child> creates "write-only" objects, so line below does not compile.
        // Child ch = l.get(0);
        // But <? extends Super> creates "read-only" objects and line below does not compile.
    }

    @Test
    void write() {
        List<Child> l = new ArrayList<>();
        // Line below does not compile as List<Child> can not be cast to List<GrandChild> because generic types are invariant.
        // addValue(l);
        addValuesWildCard(l);
        assertEquals("321", l.get(0).getValue());
    }

    // -------------- END OF: Generic Subtyping
}