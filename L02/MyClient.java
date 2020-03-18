package otherpackage;

import mypackage.MyClass;

public class MyClient {
    public static void main(String[] args) {
	MyClass c = new MyClass();
	c.sayHi();
    }
}







// java -d build MyClass.java
// jar cvf myjar.jar *
// java -cp build/myjar.jar MyClient.java


// java -d build -cp build MyClient.java
// Main-Class: MyPackage.MyClass
// jar cfm newjar.jar Manifest.txt *
// java -jar newjar.jar
