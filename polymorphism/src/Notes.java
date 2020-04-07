import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * type theory
 *
 * variance: refers to how subtyping between more complex types relates
 *           to subtyping between their components.
 *   covariant: preserves ordering
 *     Child <= Super : array(Child) <= array(Super)
 *                        Object[] arr = new Number[5];
 *                    : Func()->Child <= Func()->Super
 *                         Number Sum() fn = Double Sum();
 *   contravariant: reverses ordering
 *     Child <= Super : argument type: contravariant
 *  *                        Func(Super) <= Func(Child)
 *   variant if covariant, contravariant
 *   invariant: not variant
 *     generic
 *
 * polymorphism:
 *   ad hoc
 *   parametric
 *   subtyping
 *
 * casting
 * arrays
 * generics
 * generic arrays
 *
 */

public class Notes {

//    void generics() {
//        ArrayList<? super Integer> a = new ArrayList<>();
//        ArrayList<? super Number> b; // = a;
//        a = b;
//        b.add(new Integer(5));
//    }

    // void genericArrays2() {
       // MyNumberList[] arr = new MyIntegerList[5];
    //}

//    void aggregate(Pair<Integer> col, WriteToDb(Integer, Integer) aggr) {
//           (pair.getFirst(), pair.getSecond());
//    }
//
//    void client() {
//        aggregate(new Pair<Integer>(5.3, 7.9), WriteToDbNumber);
//    }
//
//    void SumInteger(Integer a, Integer b) {
//        int c = (int)a + (int)b;
//    }
//
//    void SumNumber(Number a, Number b) {
//        a.foo();
//    }


}
