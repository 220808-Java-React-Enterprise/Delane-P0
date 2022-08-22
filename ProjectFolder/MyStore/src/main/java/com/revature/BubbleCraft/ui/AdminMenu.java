package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.utils.Navigation;

public class AdminMenu extends Navigation implements IMenu{
    @Override
    public void start() {

        AdminMenu:
        {
            do {
                System.out.println("Welcome " + "[ADMIN]" + " to the backstage!\n" +
                        "Here you can handle and the view the workings of your shop.\n" +
                        "Choose from the options below, enter the corresponding number to move to that menu:");

                System.out.println("[1] Account\n[2] Shop\n[3] All Orders\n[4] Restock\n[5] Add New Product\n[X] Logout");
                switch (input.next().charAt(0)) {
                    case '1':
                        break;
                    case '2':
                        break;
                    case '3':
                        break;
                    case '4':
                        break;
                    case '5':
                        break;
                    case '6':
                        break;
                    case 'x':
                    case 'X':
                        return;
                    default:

                }//switch end


            } while (true);
        }//Adminmenu break label end.


    }


}//Adminmenu class end.
