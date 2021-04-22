
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Frame implements ActionListener{
	JFrame frame;
	JButton[][] buttons;
	JLabel label;
	private int size =8;
    Tas[][] table;
    char X='B';
	char O='S';
	boolean sahDurumu = false;
	Tas oynanmak_istenen_tas;
	private boolean game_end = false;
	public int _row,_column;
	Color defaultColor =new Color(226,214,120);
	Color defaultColor2 =new Color(150,77,16);
	Color oynanabilenYerRengi =new Color(255,115,115);
    private int counter=0;
	boolean flag = false;

	Tas beyazSah;
    Tas siyahSah;

	Tas[] beyaz_takim;
	Tas[] siyah_takim;


    private int getCounter(){return counter;}
    private void increaseCounter(){counter++;}
    private void setCounter(int _counter){counter = _counter;}
	
    char currentPlayer;        
    char getCurrentPlayer(){
    	return (getCounter() % 2 == 0) ? X: O;
	}
	void clearTable(){
		int counter = 0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(counter % 2==0)
					buttons[i][j].setBackground(defaultColor);
				else
					buttons[i][j].setBackground(defaultColor2);
				counter++;

			}
			counter++;
		}
	}
    void setCurrentPlayer(char player){ currentPlayer = player;}

	public Frame(Tas[][] _table,Tas [] beyaz_takim,Tas [] siyah_takim){
        table=_table;
		buttons = new JButton[8][8];
		this.beyaz_takim =beyaz_takim;
		this.siyah_takim = siyah_takim;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Icon icon;
				if(table[i][j]==null){
					buttons[i][j] = new JButton("");
				}
				else{
					if(table[i][j] instanceof Piyon){
						if(!table[i][j].beyaz_mi)
							icon = new ImageIcon("piyons.png");
						else
							icon = new ImageIcon("piyon.PNG");
						buttons[i][j] =new JButton(icon);
					}
					else if(table[i][j] instanceof Kale){
						if(!table[i][j].beyaz_mi)
							icon = new ImageIcon("kales.png");
						else
							icon = new ImageIcon("kale.PNG");
						buttons[i][j] =new JButton(icon);
					
					}
					else if(table[i][j] instanceof Vezir){ // vezir should be checked before fil
						if(!table[i][j].beyaz_mi)
							icon = new ImageIcon("vezirs.png");
						else
							icon = new ImageIcon("vezir.png");
						buttons[i][j] =new JButton(icon);
					
					}
					else if(table[i][j] instanceof At){
						if(!table[i][j].beyaz_mi)
							icon = new ImageIcon("Ats.png");
						else
							icon = new ImageIcon("At.png");
						buttons[i][j] =new JButton(icon);
					
					}
					else if(table[i][j] instanceof Fil){
						if(!table[i][j].beyaz_mi)
							icon = new ImageIcon("fils.png");
						else
							icon = new ImageIcon("fil.png");
						buttons[i][j] =new JButton(icon);
					
					}
					if(table[i][j] instanceof Sah){
						if(!table[i][j].beyaz_mi){
							icon = new ImageIcon("sahs.png");
							siyahSah = table[i][j];
						}
						else{
							icon = new ImageIcon("sah.png");
							beyazSah = table[i][j];
						}
						buttons[i][j] =new JButton(icon);
					}
					//diğer taşlar buraya gelcek
				}
				
			}
		}
		int x=0;
		int tempX=x;
		int y=0;
		int counterForColor=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				buttons[i][j].setBounds(x,y,70,100);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFocusable(false);
				buttons[i][j].setFont(new Font("Comic Sans",Font.BOLD,20));
				if(counterForColor % 2 == 0)
					buttons[i][j].setBackground(defaultColor);
				else 
					buttons[i][j].setBackground(defaultColor2);
				counterForColor++;
				x+=70;
			}
			counterForColor++;
			y+=100;
			x=tempX;
		}
		//clearTable();
	
		frame = new JFrame("Chess");
		//frame.setSize(new Dimension((_size*50) + (_size*25)+ 500,_size*50+300));
		frame.setSize(new Dimension(600,800));
        
        //undoButton.setBounds((_size*50) + (_size*25)+ 500,100,50,50);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				frame.add(buttons[i][j]);
			}
		}
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		
		//if(!game_end){
			boolean sira_siyahta_mi;
			if(counter %2 == 1){
				sira_siyahta_mi = true;
			}
			else
				sira_siyahta_mi = false;
			
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++){
					if(table[i][j]!=null && ((sira_siyahta_mi && table[i][j].beyaz_mi==false) || (!sira_siyahta_mi && table[i][j].beyaz_mi==true)) && e.getSource()==buttons[i][j] && (buttons[i][j].getBackground() == defaultColor|| buttons[i][j].getBackground() == defaultColor2)  )
					{

						clearTable();
						oynanmak_istenen_tas = table[i][j];
						_row = i;
						_column = j;
						//String player = Character.toString(getCurrentPlayer());
                        //buttons[i][j].setFont(new Font("Comic Sans",Font.BOLD,20));
						int [][] _koordinatlar=table[i][j].koordinatlar(table);
						
                        //int length_of_koordinatlar=0;	
                        /*if(table[i][j] instanceof Piyon){
                            length_of_koordinatlar = 4;
						}*/
						// -2 leri es gec -1 e kdr git noice
						
						dontLetPlayAwkardly(_koordinatlar);
						if(sahDurumu && table[i][j] instanceof Sah){ // eger sah ise castle yapılamaz su kaleyle sahon fantastık hareketı 
							for(int w=0;_koordinatlar[w][0]!= -1 ;w++){
								if(_koordinatlar[w][1] - j == 2 ||_koordinatlar[w][1] - j == -2 ){
									_koordinatlar[w][0]=-2;
									_koordinatlar[w][1]=-2;

								}
							}
						}
						else if(table[i][j] instanceof Sah){ // eger sah degılse gıdecegı yolda da engel yoksa yapabılır
							boolean flag = false,flag2=false;
							for(int w=0;_koordinatlar[w][0]!=-1;w++ ){
								if(_koordinatlar[w][0] == i && _koordinatlar[w][1] == j-1){
									flag = true;
								}
								if(_koordinatlar[w][0] == i && _koordinatlar[w][1] == j+1){
									flag2=true;
								}
							}
							if(!flag || !flag2){
								for(int w=0;_koordinatlar[w][0]!= -1 ;w++){
									if((!flag && _koordinatlar[w][1] - j == -2) || (!flag2 && _koordinatlar[w][1]-j==2) ){
										_koordinatlar[w][0]=-2;
										_koordinatlar[w][1]=-2;
	
									}
								}
							}
							/*if(!flag || !flag2){
								for(int w=0;_koordinatlar[w][0]!= -1 ;w++){
									if((!flag &&_koordinatlar[w][1] - j == 2 )||(_koordinatlar[w][1] - j == -2 && !flag2) ){
										_koordinatlar[w][0]=-2;
										_koordinatlar[w][1]=-2;
	
									}
								}
							}*/
						}
                        for(int w=0;_koordinatlar[w][0]!=-1;w++){
							if(_koordinatlar[w][0] != -2){
								//System.out.println(_koordinatlar[w][0] + " "+ _koordinatlar[w][1]);
								if(_koordinatlar[w][0]!=-1)
									buttons[_koordinatlar[w][0]][_koordinatlar[w][1]].setBackground(oynanabilenYerRengi);
							}
                        }
						
						return;
                        
					}
					else if(e.getSource()==buttons[i][j]&& buttons[i][j].getBackground() == oynanabilenYerRengi)
					{
						if(oynanmak_istenen_tas instanceof Sah && j-oynanmak_istenen_tas.y==2){
							Tas castleYapanSah = oynanmak_istenen_tas;
							oynanmak_istenen_tas = table[i][j+1];
							TasiOynat(i, j-1);
							oynanmak_istenen_tas = castleYapanSah;
						} 
						else if(oynanmak_istenen_tas instanceof Sah && j-oynanmak_istenen_tas.y==-2){
							Tas castleYapanSah = oynanmak_istenen_tas;
							oynanmak_istenen_tas = table[i][j-2];
							TasiOynat(i, j+1);
							oynanmak_istenen_tas = castleYapanSah;
						} 
						TasiOynat(i,j);
						if(oynanmak_istenen_tas instanceof Piyon && (i == 0 || i == 7)){
							Tas [] takim;
								if(i == 0){
									takim = beyaz_takim;
								}
								else 
									takim = siyah_takim;
								
							for(int w=0;w<16;w++){
								if(takim[w].x == oynanmak_istenen_tas.x && takim[w].y == oynanmak_istenen_tas.y && takim[w].beyaz_mi == oynanmak_istenen_tas.beyaz_mi){
									System.out.println("Piyon sona ulasmadıgında da bu yaziyi goruyorsan dert");
									takim[w] = table[i][j];
									break;
								}
							}
						}
						/*if((i == 0 || i == 7 )&&( oynanmak_istenen_tas instanceof Piyon)){
							new TasDegistir(table,oynanmak_istenen_tas.x, oynanmak_istenen_tas.y);
							//oynanmak_istenen_tas = table[i][j];
							//buttons[i][j].setIcon(buttons[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y].getIcon());
							mySetIcon(oynanmak_istenen_tas.x, oynanmak_istenen_tas.y);
							Tas [] takim;
							if(i == 0){
								takim = beyaz_takim;
								
							}
							else 
								takim = siyah_takim;
							for(int w=0;w<16;w++){
								if(takim[w].x == oynanmak_istenen_tas.x && beyaz_takim[w].y == oynanmak_istenen_tas.y){
									takim[w] = table[i][j];
									break;
								}
							}
						}*/
						
						clearTable();
						counter++;
						sahDurumu = false;
						//System.out.println(counter);
						int sahOlduMuVar=sahOlduMu(i,j,false); // it matters which team playing
						Tas[] _takim = null;
						
						
						if(sahOlduMuVar == 1){// siyah sah yaptı
							System.out.println("Beyaz tehlikede");
							_takim = beyaz_takim;
							// beyaz tehlikede
						}
						else if(sahOlduMuVar == -1){ // beyaz sah yaptı
							System.out.println("siyah tehlikede");
							_takim = siyah_takim;
							//siyah tehlikede
						}
						
						boolean flag = false;
						if(sahOlduMuVar!= 0){
							sahDurumu = true;
							for(int k=0;k<16;k++){
								if(_takim[k].hayatta){
									int [][] _koordinatlar_ =_takim[k].koordinatlar(table);
									oynanmak_istenen_tas = _takim[k];
									dontLetPlayAwkardly(_koordinatlar_);
									for(int w=0;_koordinatlar_[w][0] != -1 ;w++){
										
										if(_koordinatlar_[w][0]>=0){
											System.out.println(_takim[k].beyaz_mi+" "+_koordinatlar_[w][0]+" " +_koordinatlar_[w][1]);
											flag = true;
											//return;
										}
									}
									
									//flag = false;
								}
							}
							if(flag == false){
								showWinner(!_takim[0].beyaz_mi); // other team won
								return;
							}
						}
						else{ // sah yapılmadı
							boolean takimRengi = oynanmak_istenen_tas.beyaz_mi;
							if(takimRengi == true){
								_takim = siyah_takim;
							}
							else{
								_takim =beyaz_takim;
							}
							flag =false;
							System.out.println("Beyaz mi : "+ oynanmak_istenen_tas.beyaz_mi);
							for(int k=0;k<16;k++){
								if(_takim[k].hayatta){
									int [][] _koordinatlar_ =_takim[k].koordinatlar(table);
									oynanmak_istenen_tas = _takim[k];
									dontLetPlayAwkardly(_koordinatlar_);
									for(int w=0;_koordinatlar_[w][0] != -1 ;w++){
										
										if(_koordinatlar_[w][0]>=0){
											System.out.println(_takim[k].beyaz_mi+" "+_koordinatlar_[w][0]+" " +_koordinatlar_[w][1]);
											flag = true;
											//return;
										}
									}
									
									//flag = false;
								}
							}
							if(flag == false){
								showDraw(); // other team won
								return;
							}
						}

						return;
					}
					


				}

			}
			clearTable(); // butun if-elseif ler en son return yazmalı						
			
			
			
		//}
		
	}
	private int sahOlduMu(int i,int j, boolean noMatterWhichTeam ){
		int[][] _koordinatlar = table[i][j].koordinatlar(table);
		for(int k=0;_koordinatlar[k][0] != -1 ;k++){
			if((table[i][j].beyaz_mi == false || noMatterWhichTeam)&&  _koordinatlar[k][0] == beyazSah.x && _koordinatlar[k][1] == beyazSah.y){
				return 1; // siyah sah yapmıs
			}
			else if((table[i][j].beyaz_mi || noMatterWhichTeam )&& _koordinatlar[k][0] == siyahSah.x && _koordinatlar[k][1] == siyahSah.y){
				return -1; // beyaz sah yapmıs
	        }
		}
		return 0; // kimse sah yapmamıs
	}
	private void dontLetPlayAwkardly(int[][] _koordinatlar){
		Tas[] takim;
		if(oynanmak_istenen_tas.beyaz_mi == true)
			takim = siyah_takim;
		else 
			takim = beyaz_takim;
		int oldX=oynanmak_istenen_tas.x;
		int oldY=oynanmak_istenen_tas.y;
		boolean baslangicta =oynanmak_istenen_tas.baslangicta_mi;

		Tas yenilenTas = null;
		for(int k = 0;_koordinatlar[k][0] != -1;k++){ 
			// suan ısaretlı olan tasın gıtmeye tenezzul edecegı yerler bunları azaltmaya calısıyoruz
			// eger yaptıgımız hareket karsı tarafın sah yapmasını saglıyorsa bu hareketı yapamayız o yuzden 
			// _koordinatlar[k][0] = -2 yapcaz
			if(table[_koordinatlar[k][0]][_koordinatlar[k][1]] != null){
				yenilenTas = table[_koordinatlar[k][0]][_koordinatlar[k][1]];
			}									
			boolean sahMi = TasiOynatButtonlariDegistirmeden(_koordinatlar[k][0],_koordinatlar[k][1],takim); // tası oraya oynıcak , tas varsa oldurcek yerıne onu koycak , sah yapıyor mu onu dondurcek
			tabloyuEskiHalineGetir(oldX,oldY,baslangicta,_koordinatlar[k][0],_koordinatlar[k][1],yenilenTas);//if(yenilenTas!=null)yenilenTas.hayattamı=true
			
			if(sahMi){
				_koordinatlar[k][0] = -2;
				_koordinatlar[k][1] = -2;
			}
			yenilenTas =null;
		}
	   	
	}
	private void TasiOynat(int _i,int _j){
		if(table[_i][_j]!= null )
			table[_i][_j].hayatta = false; // bu bozdu kırmızı olan yer sılınıyor usttekı fonksıyondan oturu
		
		//Tas temp = oynanmak_istenen_tas;
		table[_i][_j] = oynanmak_istenen_tas;
		table[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y] = null;
		buttons[_i][_j].setIcon(buttons[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y].getIcon());
		
		buttons[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y].setIcon(null);
		oynanmak_istenen_tas.oyna(_i,_j);
		//********* */
		if((_i == 0 || _i == 7 )&&( oynanmak_istenen_tas instanceof Piyon)){
			
			new TasDegistir(_i, _j);	
			
			/*while(true){
				if(!(table[_i][_j] instanceof Piyon)){
					break;
				}
			}*/
			if(table[_i][_j] instanceof Vezir){
				System.out.println("Vezire donusmus");
			}
			else if (table[_i][_j] instanceof Piyon){
				System.out.println("Piyon olarak kalmıs");

			}
			else{
				System.out.println("Sacma sapan bisi");

			}
			mySetIcon(_i, _j);
		}
		/*if((_i == 0 || _i == 7 )&&( oynanmak_istenen_tas instanceof Piyon)){
			new TasDegistir(table,_i, _j);
			//oynanmak_istenen_tas = table[i][j];
			//buttons[i][j].setIcon(buttons[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y].getIcon());
			mySetIcon(_i, _j);
			Tas [] takim;
			if(_i == 0){
				takim = beyaz_takim;
				
			}
			else 
				takim = siyah_takim;
			for(int w=0;w<16;w++){
				if(takim[w].x == oynanmak_istenen_tas.x && beyaz_takim[w].y == oynanmak_istenen_tas.y){
					takim[w] = table[_i][_j];
					break;
				}
			}
	}*/
}
private void TasDegistirici(int _i,int _j){
	new TasDegistir(_i, _j);
}
	boolean TasiOynatButtonlariDegistirmeden(int _i,int _j,Tas[] takim){
		if(table[_i][_j]!= null )
			table[_i][_j].hayatta = false;
		
		//Tas temp = oynanmak_istenen_tas;
		table[_i][_j] = oynanmak_istenen_tas;
		table[oynanmak_istenen_tas.x][oynanmak_istenen_tas.y] = null;
		oynanmak_istenen_tas.oyna(_i,_j);
		for(int a=0;a<16;a++){
			if(takim[a].hayatta){
				int[][] koordinatlar = takim[a].koordinatlar(table);
				boolean sahVarMiKoordinatlarda=sahKoordinatlardaMi(koordinatlar);
				if(sahVarMiKoordinatlarda){
					return true; // sah oluyor
				}
			}
		}
		return false; // sah yok
	}
	void tabloyuEskiHalineGetir(int oynananTasX,int oynananTasY,boolean baslangicta,int _i,int _j,Tas yenilenTas){
		if(yenilenTas!= null )
			yenilenTas.hayatta = true;
		table[_i][_j] = yenilenTas;
		oynanmak_istenen_tas.x=oynananTasX;
		oynanmak_istenen_tas.y = oynananTasY;
		oynanmak_istenen_tas.baslangicta_mi = baslangicta;

		table[oynananTasX][oynananTasY] = oynanmak_istenen_tas;
	}
	boolean sahKoordinatlardaMi(int [][]koordinatlar){
		for(int a=0;koordinatlar[a][0]!=-1;a++){
			if((beyazSah.x == koordinatlar[a][0] && beyazSah.y ==koordinatlar[a][1]) || (siyahSah.x == koordinatlar[a][0] && siyahSah.y ==koordinatlar[a][1]) ){
				return true;
			}
		}
		return false;
	}
	private void showWinner(boolean team){
		JFrame frame = new JFrame("Congratulations!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,150);
		String winnerTeam =(team) ? "beyaz" : "siyah";
		JLabel label = new JLabel("Winner :" + winnerTeam);
		frame.add(label);
		frame.setVisible(true);
	
	}
	private void showDraw(){
		JFrame frame = new JFrame("Peace Has Won");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,150);
		JLabel label = new JLabel("Good Game Both Sides");
		frame.add(label);
		frame.setVisible(true);
	
	}
	private class TasDegistir extends JFrame implements ActionListener{
	    JRadioButton kale,at,fil,vezir;    
	    JButton b;    
		int x,y;
		//boolean beyaz;

	    TasDegistir(int i,int j){
			/*System.out.println(" Tas degıstıre gelen konum :"+i+" "+j);
			x=i;
			y=j;
	        kale=new JRadioButton("Kale");    
	        kale.setBounds(100,50,150,30);      
	        at=new JRadioButton("At");    
	        at.setBounds(100,100,200,30);
			fil=new JRadioButton("Fil");    
	        fil.setBounds(100,150,150,30);      
	        vezir=new JRadioButton("Vezir");    
	        vezir.setBounds(100,200,150,30);      
	        
			ButtonGroup bg=new ButtonGroup();    
	        bg.add(kale);
	        bg.add(at);   
			bg.add(fil);
			bg.add(vezir); 

	        b=new JButton("Okey");    
	        b.setBounds(100,250,80,30);    
	        b.addActionListener(this);    
	        add(kale);add(at);add(fil);add(vezir);add(b);    
	        setSize(400,400);    
	        setLayout(null);    
	        setVisible(true); */
			//------------------
			Tas tas=null; 
			if(i == 0){
				tas = new Vezir(i,j,true);
				System.out.println("Beyaz Vezir secildi hemde");
			}
			else{
				tas = new Vezir(i,j,false);
			}
			if(tas instanceof Vezir)
				System.out.println("Vezir tabloya konuldu");
			System.out.println("table :"+i+" "+ j+" e vezir koyuldu");
			table[i][j] = tas;
			//------------------
			 
	    }    
	    public void actionPerformed(ActionEvent e){    
	        Tas tas=null; 
			if(kale.isSelected()){    
	            //JOptionPane.showMessageDialog(this,"You are playing vs Player");    
	            if(x == 0){
					tas = new Kale(x,y,true);
				}
				else{
					tas = new Kale(x,y,false);
				}
	        }    
	        else if(at.isSelected()){    
	            //JOptionPane.showMessageDialog(this,"You are playing vs Computer");    
	            if(x == 0){
					tas = new At(x,y,true);
				}
				else{
					tas = new At(x,y,false);
				}
	            
	        }
			else if(fil.isSelected()){    
	            //JOptionPane.showMessageDialog(this,"You are playing vs Computer");    
	            if(x == 0){
					tas = new Fil(x,y,true);
				}
				else{
					tas = new Fil(x,y,false);
				}
	            
	        } 
			else if(vezir.isSelected()){    
	            //JOptionPane.showMessageDialog(this,"You are playing vs Computer");    
	            System.out.println("Vezir secildi");
				if(x == 0){
					tas = new Vezir(x,y,true);
					System.out.println("Beyaz Vezir secildi hemde");
				}
				else{
					tas = new Vezir(x,y,false);
				}
	           
	        } 
			
			
			
			table[x][y] = tas;
			flag = true;
	        super.dispose(); // closes window
	        //flag = true; // program can go to the next step input was taken
	    }
	}
	void mySetIcon(int i,int j){
		Icon icon = null;
		
		if(table[i][j] instanceof Kale){
			if(!table[i][j].beyaz_mi)
				icon = new ImageIcon("kales.png");
			else
				icon = new ImageIcon("kale.PNG");
			//buttons[i][j] =new JButton(icon);
		
		}
		else if(table[i][j] instanceof Vezir){ // vezir should be checked before fil
			System.out.println("Icon olusturuldu");
			
			if(!table[i][j].beyaz_mi)
				icon = new ImageIcon("vezirs.png");
			else
				icon = new ImageIcon("vezir.png");
	//		buttons[i][j] =new JButton(icon);
		
		}
		else if(table[i][j] instanceof At){
			if(!table[i][j].beyaz_mi)
				icon = new ImageIcon("Ats.png");
			else
				icon = new ImageIcon("At.png");
	//		buttons[i][j] =new JButton(icon);
		
		}
		else if(table[i][j] instanceof Fil){
			if(!table[i][j].beyaz_mi)
				icon = new ImageIcon("fils.png");
			else
				icon = new ImageIcon("fil.png");
	//		buttons[i][j] =new JButton(icon);
		
		}
		System.out.println("Icon yerlestırıldı");
		buttons[i][j].setIcon(icon);
	}
}

	
    