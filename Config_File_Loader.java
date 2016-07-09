package Sokoban;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config_File_Loader {


//zmienne -----------------------------------------------------------------------
	
		/**
		 * nazwa pliku z ktorego odczytana bedzie plansza
		 */
		private String file_name;

		/**
		 *ilosc elementow w pionie  
		 */
		private int height; // wysokoœæ
	/*
	 * ilosc elementow w poziomie
	 */
		private int width; // szerokoœæa
	/*
	 * tablica zawieraj¹ca symboliczny rozk³ad planszy
	 */
		public char level_table[][];// tablica zawieraj¹ca rozk³ad planszy
		

		

//funckje ----------------------------------------------------------------------------------------------------

		/**
		 * konstruktor klasy Config_File_Loader
		 */
		public Config_File_Loader(String name_of_the_file) {
			file_name=name_of_the_file;
			
			try {
				load_data();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Funkcja wczytuj¹ca dane z pliku konfiguracyjnego i zapisuj¹ca je do
		 * zmiennych tej klasy
		 */
		public void load_data() throws IOException {
			// zmienne
			String[] parts;
			String line = "";
			char[] level_table_tmp;
			short a = 0;
			int sign;
//System.out.println(file_name);
			FileReader fr = new FileReader(file_name);
			BufferedReader br = new BufferedReader(fr);

			
			// parsowanie pocz¹tkowych linii
			line = br.readLine();

			parts = line.split(" ");
			width = (short) Integer.parseInt(parts[2]);

			line = "";
			line = br.readLine();

			parts = line.split(" ");
			height = (short) Integer.parseInt(parts[2]);

			// inicjlalizacja tablic
			level_table = new char[height][width];
			level_table_tmp = new char[width * height];

			// wczytanie planszy
			while ((sign = br.read()) != -1) {
				if (sign > 32 && sign <= 126)
					level_table_tmp[a++] = (char) sign;// tu cos nie trybi
			}
			// wpisanie planszy do tablicy 2- wymiarowej
			short x = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++)
				{
					level_table[i][j] = level_table_tmp[x++];
					//System.out.print(level_table[i][j]);
				}
				//System.out.println();
			}

			br.close();
		}

		/*
		 * getter pola height
		 */
		public int getHeight() { return height; }
		/*
		 * getter pola width
		 */
		public int getWidth()  { return width;  }
		
	}


