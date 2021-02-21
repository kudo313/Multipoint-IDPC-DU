package Multipoint;
import java.util.ArrayList;
public class CheckNotAlive {
    public boolean check = false;
    public ArrayList<NotAliveNodes> a1 ;
    public ArrayList<NotAliveNodes> a2;
    public CheckNotAlive(ArrayList<NotAliveNodes> a1,ArrayList<NotAliveNodes> a2){
        this.a1 = a1;
        this.a2 = a2;
        for (NotAliveNodes tmp1 : a1){
            for (NotAliveNodes tmp2 : a2){

                if (tmp2.elementNotAlive.get(0) == tmp1.elementNotAlive.get(0)){
                    if (tmp2.elementNotAlive.get(1) == tmp1.elementNotAlive.get(1)){
                        check = true;
                    }
                }
            }
        }
    }
}
