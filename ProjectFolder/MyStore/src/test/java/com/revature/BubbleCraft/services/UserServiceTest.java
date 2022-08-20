package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;
import javafx.beans.binding.When;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    //Adding an instance of the system to test as a data field so the test class can access it.
    private UserService sut; //S.U.T System Under Test

    //Mockito
    //A mock database (used for testing only.)
    private final UserDAO mockUserDao = mock(UserDAO.class);

    //A constructor class can not be created for a test class so one must be simulated using the @Before annotation.
    @Before
    public void Setup() {

        sut = new UserService(mockUserDao); //Passing in a fake db for testing.
    }

    /*Test cases use annotations to know how to and when to run test cases.
    Good practice not: always start the names of your test classes with 'test_'
    Structure of a test case: Arrange; the test case, Act; out the test, Assert; the result.*/

    @Test (expected = NullPointerException.class)//The annotation goes before each test method.
    public void test_isValidUsername_givenCorrectUsername() {
        //ARRANGE
        String validUsername = "Honey"; //NOTE: Having more than one test case in a single test method, might cause an exception to hasppen.

        //ACT
        boolean flag1 = sut.isValidUserName(validUsername);

        //ASSERT
        Assert.assertTrue(flag1);

    }

    @Test (expected = NotValidException.class) //Do this whenever you expect you test case to result in an exception.
    public void test_isValidUsername_givenIncorrectUsername() {
        //ARRANGE
        String inValidUsername = "Dg";
        //ACT
        sut.isValidUserName(inValidUsername);
        //ASSERT //Implicit in this case?

    }

    @Test ( expected = NullPointerException.class )
    public void test_Login_givenCorrectInput() {

        //Spy: spies are partial mocks they are use when testing methods within methods.
        UserService SpiedSut = Mockito.spy(sut);

        //ARRANGE
        String validEmail = "Honey@aol.com";
        String validPassword = "letmethinK8*";

        //More Mockito... Using a spy.
        when(SpiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(SpiedSut.isValidEmail(validEmail)).thenReturn(true);
        when(mockUserDao.getUserByEmailAndPassword( validEmail, validPassword)).thenReturn( new User());

        //ACT
        User user = sut.Login( validEmail, validPassword);

        //ASSERT
        Assert.assertNotNull(user);


    }


}