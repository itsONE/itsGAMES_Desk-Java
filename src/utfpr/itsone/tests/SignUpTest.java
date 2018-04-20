package utfpr.itsone.tests;

import utfpr.itsone.model.core.User;

import static org.junit.jupiter.api.Assertions.*;

class SignUpTest {

    @org.junit.jupiter.api.Test
    void valData() {
        String[] user = {"admin","admin","admin","admin@utfpr.com"};
        assertEquals("",user);
    }
}