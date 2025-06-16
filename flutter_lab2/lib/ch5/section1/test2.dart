import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Form Test'),),
        body: TestScreen(),
      ),
    );
  }
}

class TestScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestScreenState();
  }
}

class TestScreenState extends State<TestScreen>{
  //키로 어떤 위젯의 state 를 획득하려면 GlobalKey 이용해야, LocalKey 는 안된다..
  var formKey = GlobalKey<FormState>();
  String? firstName;
  String? lastName;
  bool? isChecked = false;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Form(
          key: formKey,
          child: Column(
            children: [
              //Form 을 사용하면.. Form 에서 유저 입력 위젯과 상호 연동(validator, onSaved함수 호출)
              //등을 도와준다.. 그냥.. TextField 이면 Form 연동 부분이 준비안되어 있다..
              //꼭 FormField 를 이용해야 한다..
              //제네릭 타입은.. 입력 데이터 타입..
              FormField<String>(
                initialValue: '',
                //Form State 의 validate() 호출하는 순간 자동 호출..
                //매개변수는 유저가 입력한 데이터.. 제네릭 타입의 데이터..
                validator: (value){
                  if(value == null || value.isEmpty){
                    return 'firstName 을 입력해 주세요.';//invalid 상황.. 유저 에러 메시지..
                  }
                  return null;//valid
                },
                //화면 구성...
                builder: (FormFieldState<String> state){
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      TextField(
                        onChanged: (value){
                          state.didChange(value);//FormField 에 입력값 변경을 알림..
                        },
                        decoration: InputDecoration(
                          labelText: 'First Name',
                          errorText: state.errorText,//FormField 에서 가지고 있는 에러 메시지 출력
                          border: OutlineInputBorder(),
                        ),
                      )
                    ],
                  );
                },
                //Form State 의 save() 함수 호출되는 순간.. 유저 입력 데이터를 매개변수로..
                onSaved: (value){
                  firstName = value;
                },
              ),
              //FormField 에 제네릭 타입으로.. 입력 데이터 명시하고.. builder 에..
              //화면 구성을 위한 TextField 를 준비하는 것이 기본인데..
              //이 둘을 추상화 시킨.. TextFormField 도 제공한다..
              TextFormField(
                decoration: InputDecoration(
                  labelText: 'Last Name',
                ),
                validator: (value){
                  if(value?.isEmpty ?? false){
                    return "lastName 을 입력해 주세요.";
                  }
                  return null;
                },
                onSaved: (value){
                  lastName = value;
                },
              ),
              FormField<bool>(
                initialValue: false,
                validator: (value){
                  if(value != true){
                    return '동의해야 합니다.';
                  }
                  return null;
                },
                builder: (FormFieldState<bool> state){
                  return CheckboxListTile(
                    title: Text('약관 동의'),
                    value: state.value ?? false,
                    onChanged: state.didChange,
                    subtitle: state.hasError
                        ? Text(state.errorText!, style: TextStyle(color: Colors.red),)
                        : null,
                  );
                },
                onSaved: (value){
                  isChecked = value;
                },
              )
            ],
          ),
        ),
        ElevatedButton(
            onPressed: (){
              //key 를 이용해서 Form 의 State 객체 획득..
              //validate() : Form 하위의 모든 구성요소의 validator() 호출..
              //모두 null 리턴 : 전체 true : 모두 유효하다..
              if(formKey.currentState?.validate() ?? false){
                //모든 onSaved() 함수를 호출한다..
                formKey.currentState?.save();
                print("firstName: $firstName, lastName: $lastName, isChecked: $isChecked");
              }
            },
            child: Text('submit'),
        )
      ],
    );
  }
}