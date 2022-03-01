package satranc;
public class Kale extends Tas{
    Kale(int _x,int _y,boolean _beyaz_mi){
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
        return kaleVeVezirIcinGidilebilecekYerler(tahta);
        /*
        int [][] _koordinatlar = new int[15][2]; // en fazla gidebileceği 14 yer var +1 -1 için
        int i=0;
       

        for(int a=x+1;a<8;a++){ // asagı gıdebilme
            System.out.println(a+"+"+y);
            if(tahta[a][y] == null){
                _koordinatlar[i][0]=a;
                _koordinatlar[i][1]=y;
                i++;
            }
            else if(tahta[a][y].beyaz_mi != beyaz_mi){
                _koordinatlar[i][0]=a;
                _koordinatlar[i][1]=y;
                i++;
                break;
            }
            else if(tahta[a][y].beyaz_mi == beyaz_mi){ // aynı renkte taş goruldu
                break;
            }
        }
        for(int a=x-1;a>=0;a--){ // yukarı gıtme
            if(tahta[a][y]== null){
                _koordinatlar[i][0]=a;
                _koordinatlar[i][1]=y;
                i++;
            }
            else if(tahta[a][y].beyaz_mi != beyaz_mi){
                _koordinatlar[i][0]=a;
                _koordinatlar[i][1]=y;
                i++;
                break;
            }
            else if(tahta[a][y].beyaz_mi == beyaz_mi){ // aynı renkte taş goruldu
                break;
            }
        }
        for(int a=y+1;a<8;a++){ // saga gidebilme
            if(tahta[x][a] == null){
                _koordinatlar[i][0]=x;
                _koordinatlar[i][1]=a;
                i++;
            }
            else if(tahta[x][a].beyaz_mi != beyaz_mi){
                _koordinatlar[i][0]=x;
                _koordinatlar[i][1]=a;
                i++;
                break;
            }
            else if(tahta[x][a].beyaz_mi == beyaz_mi){ // aynı renkte taş goruldu
                break;
            }
        }
        for(int a=y-1;a>=0;a--){ // yukarı gıtme
            if(tahta[x][a]== null){
                _koordinatlar[i][0]=x;
                _koordinatlar[i][1]=a;
                i++;
            }
            else if(tahta[x][a].beyaz_mi != beyaz_mi){
                _koordinatlar[i][0]=x;
                _koordinatlar[i][1]=a;
                i++;
                break;
            }
            else if(tahta[x][a].beyaz_mi == beyaz_mi){ // aynı renkte taş goruldu
                break;
            }
        }
        _koordinatlar[i][0]=-1;
        _koordinatlar[i][1]=-1;
        
        return _koordinatlar;
        */
     }
}
