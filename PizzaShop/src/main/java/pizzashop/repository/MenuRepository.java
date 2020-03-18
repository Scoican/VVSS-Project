package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private List<MenuDataModel> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        File file = new File("C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\menu.txt");
        this.listMenu= new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MenuDataModel getMenuItem(String line){
        MenuDataModel item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price);
        return item;
    }

    public List<MenuDataModel> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }

}
