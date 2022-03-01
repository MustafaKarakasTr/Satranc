package satranc;
public abstract class Tas{
    protected int x,y;
     abstract int[][] koordinatlar(Tas[][] tahta);
    protected boolean hayatta = true;
    protected boolean beyaz_mi;
    protected boolean baslangicta_mi=true;
    public void setBaslangicta(boolean _baslangicta){ baslangicta_mi = _baslangicta;}
    protected boolean oyna(int _x,int _y){  
        
        if(_x>=0 && _x<8 && _y>=0 && _y<8 )
        {
            x = _x;
            y= _y;       
            return true;
        }
        return false;
    }
    boolean validPointtoGo(Tas[][] tahta,int _x,int _y){
        if(_x>=0 &&_x<8 &&_y>=0 &&_y<8 &&( tahta[_x][_y] == null ||tahta[_x][_y].beyaz_mi!=beyaz_mi) ){
            return true;
        }
        return false;
    }
    public Tas(int _x,int _y,boolean _beyaz_mi){
        x = _x;
        y= _y;   
        beyaz_mi = _beyaz_mi;
    } 
    void yenildi(){
        hayatta = false;
    }
    
    protected int[][] kaleVeVezirIcinGidilebilecekYerler(Tas [][] tahta){    
        int [][] _koordinatlar = new int[15][2];
        _koordinatlar[0][0]=-1;
        _koordinatlar[0][1]=-1;

        if(this instanceof Kale || this instanceof Vezir){
             // en fazla gidebileceği 14 yer var +1 -1 için
            int i=0;
        

            for(int a=x+1;a<8;a++){ // asagı gıdebilme
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
        }
        return _koordinatlar;
     }
     
    
}
