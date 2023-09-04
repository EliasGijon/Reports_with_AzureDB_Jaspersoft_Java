package Main;

import GUI.gui;
import sql_Insertpack.Frame_Insert_Data;
import sql_Insertpack.Insert_Products;

public class Reports{
    public static void main(String[] args) {
        gui inicio=new gui();
        //inicio.setVisible(true);
        
        //Frame_Insert_Data form=new Frame_Insert_Data();
        Insert_Products form=new Insert_Products();
        form.setVisible(true);
    }
}