* var1 package:
  * Implements multiple purpose Node class handling both numeric values and binary operators
* var2 package:
  * Splits numeric and operator nodes into separate hierarchies both implementing Node interface
  * Each of +, -, *, / operators now have corresponding class: PlusNode, MinusNode, MultiplyNode, DivideNode
* var3 package:
  * Introduces BinaryOpNode in the hierarchy and PlusNode, MinusNode, ... classes inherit from it.
    * This removes code duplication from constructors
    * Also implements toString method which delegates getting operator character to subclasses
    * And evaluate method which delegates actual evaluation to new abstract doEvaluate method
  * Replaces DoubleValue and IntValue nodes with single generic ValueNode  