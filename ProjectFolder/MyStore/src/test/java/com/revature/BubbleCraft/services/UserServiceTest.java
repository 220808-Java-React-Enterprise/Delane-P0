package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;
import com.revature.BubbleCraft.utils.customexceptions.NullUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    //Adding an instance of the system to test as a data field so the test class can access it.
    private UserService sut;

    //Mockito
    private static final UserDAO mockUserDao = mock(UserDAO.class);

   @Before
    public void Setup() {

        sut = new UserService(mockUserDao); //Passing in a fake db for testing.
    }

    @Test
    public void test_isValidPassword_givenCorrectPassword() {
        //ARRANGE
        String validPassword = "Th!5iSv@liD";

        //ACT
        boolean flag1 = sut.isValidPassword(validPassword);

        //ASSERT
        Assert.assertTrue(flag1);

    }


    @Test (expected = NotValidException.class)
    public void test_isValidPassword_givenIncorrectPassword() {
        //ARRANGE
        String inValidPassword = "++++++++";
        //ACT
        boolean flag2 = sut.isValidPassword(inValidPassword);

        //ASSERT
        Assert.assertFalse(flag2);

    }

    @Test
    public void test_isValidPhone_givenIdealInput() {
        String validPhone = "234-567-8901";
        Assert.assertTrue(sut.isValidPhone(validPhone));

    }
    @Test
    public void test_isValidPhone_givenCorrectPhone() {
        String validPhone = "2345678901";
        Assert.assertTrue(sut.isValidPhone(validPhone));

    }

    @Test (expected = NotValidException.class)
    public void test_isValidPhone_givenIncorrectPhone() {
        String inValidPhone = "1-5556787890";
        Assert.assertFalse(sut.isValidPhone(inValidPhone));

    }

    @Test (expected = NotValidException.class)
    public void test_isValidPhone_givenLettersInPhone() {
        String inValidPhone = "1-900-404-Help";
        Assert.assertFalse(sut.isValidPhone(inValidPhone));

    }

    @Test
    public void test_Login_givenCorrectInput() {

        //ARRANGE
        String validEmail = "Honey@aol.com";
        String validPassword = "letmethinK8*";

        when(mockUserDao.getUserByEmailAndPassword( validEmail, validPassword)).thenReturn( new User());

        //ACT
        User user = sut.Login( validEmail, validPassword);

        //ASSERT
        Assert.assertNotNull(user);

    }


    @Test (expected = NullUserException.class)
    public void test_Login_givenInCorrectInput() {

        //ARRANGE
        String validEmail = "Joijsm@somewhere.something.somehow";
        String validPassword = "SomethingvaliDbutunlisted9%*";

        when(mockUserDao.getUserByEmailAndPassword( validEmail, validPassword)).thenReturn( null);

        //ACT
        User user = sut.Login( validEmail, validPassword);

        //ASSERT
        Assert.assertNull(user);

    }




}