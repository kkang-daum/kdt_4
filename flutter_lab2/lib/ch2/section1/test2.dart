// import 'outer.dart';

// import 'outer.dart' as A;//별칭으로 import..

// import 'outer.dart' as A show no1, sayHello1;

import 'outer.dart' as A hide no1, sayHello1;
main(){
  // no1 = 20;//error
  // A.no1 = 10;
  // A.sayHello1();
  A.User1 obj;
}