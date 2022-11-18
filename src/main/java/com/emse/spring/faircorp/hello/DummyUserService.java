package com.emse.spring.faircorp.hello;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService{

    private GreetingService GS;

    @Autowired
    public DummyUserService(GreetingService GS) {
        this.GS = GS;
    }

    public void greetAll(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Elodie");
        names.add("Charles");

        for (int i=0;i<names.size();i++){
            GS.greet(names.get(i));
        }
    }
}
