public class Tahta {
    Tas[] beyaz_takim=new Tas[16];
    Tas[] siyah_takim=new Tas[16];
    //boolean[][] tas_var_mi = new boolean[8][8]; // bir yerde tas varsa true yoksa false
    Tas[][] tahta = new Tas[8][8];// bu taslarimizi gosteren referanslari tutacak tas yoksa oraya null atmaliyiz
    Frame frame;
    

    Tahta(){
        for(int i=0;i<8;i++){
            beyaz_takim[i] =new Piyon(6,i,true); // true ise  beyaz
            siyah_takim[i] =new Piyon(1,i,false);
           // tas_var_mi[1][i]=true;
            //tas_var_mi[6][i]=true;
            tahta[6][i]=beyaz_takim[i];
            tahta[1][i]=siyah_takim[i];
        }
        
        beyaz_takim[8] = new Kale(7,0, true);
        beyaz_takim[9] = new Kale(7,7, true);
        tahta[7][0] = beyaz_takim[8];
        tahta[7][7] = beyaz_takim[9];
        // tas_var_mi[7][0] = true;
        // tas_var_mi[7][7] = true;
        
        siyah_takim[8] = new Kale(0,0, false);
        siyah_takim[9] = new Kale(0,7, false);
        tahta[0][0] = siyah_takim[8];
        tahta[0][7] = siyah_takim[9];
      //   tas_var_mi[0][0] = true;
       //  tas_var_mi[0][7] = true;

        //-------------
        beyaz_takim[10] = new At(7,1, true);
        beyaz_takim[11] = new At(7,6, true);
        tahta[7][1] = beyaz_takim[10];
        tahta[7][6] = beyaz_takim[11];
       //  tas_var_mi[7][1] = true;
       //  tas_var_mi[7][6] = true;
        
        siyah_takim[10] = new At(0,1, false);
        siyah_takim[11] = new At(0,6, false);
        tahta[0][1] = siyah_takim[10];
        tahta[0][6] = siyah_takim[11];
      //   tas_var_mi[0][1] = true;
       //  tas_var_mi[0][6] = true;
        //-------------
        beyaz_takim[12] = new Fil(7,2, true);
        beyaz_takim[13] = new Fil(7,5, true);
        tahta[7][2] = beyaz_takim[12];
        tahta[7][5] = beyaz_takim[13];
       //  tas_var_mi[7][2] = true;
       //  tas_var_mi[7][5] = true;

        siyah_takim[12] = new Fil(0,2, false);
        siyah_takim[13] = new Fil(0,5, false);
        tahta[0][2] = siyah_takim[12];
        tahta[0][5] = siyah_takim[13];
       //  tas_var_mi[0][2] = true;
        // tas_var_mi[0][5] = true;
        //--------
        //vezirler
        beyaz_takim[14] = new Vezir(7,3, true);
        tahta[7][3] = beyaz_takim[14];
       //  tas_var_mi[7][3] = true;
        
        siyah_takim[14] = new Vezir(0,3, false);
        tahta[0][3] = siyah_takim[14];
       //  tas_var_mi[0][3] = true;
        //------
        beyaz_takim[15] = new Sah(7,4, true);
        //beyazSah = beyaz_takim[15];
        tahta[7][4] = beyaz_takim[15];
        // tas_var_mi[7][4] = true;
        
        siyah_takim[15] = new Sah(0,4, false);
        //siyahSah = siyah_takim[15];
        tahta[0][4] = siyah_takim[15];
       //  tas_var_mi[0][4] = true;
        //----------
        
        /*char [][]arr=new char[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                arr[i][j] = '.';
            }
        }*/
        
        frame = new Frame(tahta,beyaz_takim,siyah_takim);
        

    }

}
