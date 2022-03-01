package satranc;
public class Sah extends Tas {
    Sah(int _x,int _y,boolean _beyaz_mi){
        super(_x,_y,_beyaz_mi);// koordinatlarını koyar
        //System.out.println(x+"? "+y);
    }
    protected boolean oyna(int _x,int _y){
        boolean a=super.oyna(_x,_y);
        if(baslangicta_mi)
            baslangicta_mi = false;
        return a;
    }
     int[][] koordinatlar(Tas[][] tahta){
        int [][] helperIndexes = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
        int [][] _koordinatlar = new int[11][2]; 
        int k=0;
        for(int i=0;i<8;i++){
            if(validPointtoGo(tahta, x+helperIndexes[i][0], y+helperIndexes[i][1])){
                _koordinatlar[k][0] = x+helperIndexes[i][0];
                _koordinatlar[k][1] = y+helperIndexes[i][1];
                k++;

            }
        }
        if(baslangicta_mi)// to castle Sah should not have moved
        {
            Tas kale1= tahta[x][y-4];
            if(kale1 != null && kale1.baslangicta_mi == true && tahta[x][y-1] == null &&  tahta[x][y-2] == null && tahta[x][y-3] == null ){
                
                _koordinatlar[k][0] = x;
                _koordinatlar[k][1] = y-2;
                k++;
            }
            kale1 = tahta[x][y+3];
            if(kale1 != null && kale1.baslangicta_mi == true && tahta[x][y+1] == null &&  tahta[x][y+2] == null){
                _koordinatlar[k][0] = x;
                _koordinatlar[k][1] = y+2;
                k++;
            }


        }
        _koordinatlar[k][0] = -1;
        _koordinatlar[k][1] = -1;
        return _koordinatlar;


     }
}
