package com.example.ch6.section6;

//custom view 개발자 입장의 코드..
//자신의 custom view 이벤트를 전파 받고 싶은 액티비티가 있다면 이 인터페이스 구현해서 등록하라
public interface OnMyChageListener {
    void onChange(int value);
}
