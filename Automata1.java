import javax.swing.JOptionPane;

public class Automata1 {
	
	public char [] validos1 = {'0','1','2','3','4',
							  '5','6','7','8','9',
							  '_',
							  'a','b','c','d','e',
							  'f','g','h','i','j',
							  'k','l','m','n','o',
							  'p','q','r','s','t',
							  'u','v','w','x','y',
							  'z',
							  'A','B','C','D','E',
							  'F','G','H','I','J',
							  'K','L','M','N','O',
							  'P','Q','R','S','T',
							  'U','V','W','Q','Y',
							  'Z'};
	public char[] validos2 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	
	//Hay 37 elementos en el array characteres
	
	//Hay 10 elementos en el array numbers 
	
	public String identificador(String palabra) {
		
		char [] palabraCh = palabra.toCharArray();
		int contador; 
		
		//Que la primer palabra no sea numero
		for (int i =0 ; i<10 ; i++) {
			if(this.validos1[i] == palabraCh[0]) {
				return identificador2(palabra);
			}
		}
		
		//Que la palabra solo tenga valores alfabeticos, numericos y guion bajo
		//Que la palabra tenga al menos un valor alfabetico
		
		int contadorAMUC = 0;
		
		for (int i = 0 ; i<palabraCh.length ;i++) {
			contador = 0;
			for (int j = 0; j<this.validos1.length; j++) {
				if(this.validos1[j] == palabraCh[i]) {
					contador++;
				}
				if((this.validos1[j] == palabraCh[i])&& j>11) {
					contadorAMUC++;
				}
				
				try {
					if(palabraCh[i] == 'b') {
						if (palabraCh[i+1] == 'e' && palabraCh[i+2] == 'g' && palabraCh[i+3] == 'i' && palabraCh[i+4] == 'n') {
							return "Invalido";
						}
					}
					if(palabraCh[i] == 'e') {
						if (palabraCh[i+1] == 'n' && palabraCh[i+2] == 'd') {
							return "Invalido";
						}
					}
					if(palabraCh[i] == 'e') {
						if (palabraCh[i+1] == 'l' && palabraCh[i+2] == 's' && palabraCh[i+3] == 'e') {
							return "Invalido";
						}
					}
					if(palabraCh[i] == 'p') {
						if (palabraCh[i+1] == 'r' && palabraCh[i+2] == 'o' && palabraCh[i+3] == 'g' &&
							palabraCh[i+4] == 'r' && palabraCh[i+5] == 'a' && palabraCh[i+6] == 'm') {
							return "Invalido";
						}
					}
					if(palabraCh[i] == 'v') {
						if (palabraCh[i+1] == 'a' && palabraCh[i+2] == 'r') {
							return "Invalido";
						}
					}	
					if(palabraCh[i] == 'i') {
						if (palabraCh[i+1] == 'f') {
							return "Invalido";
						}
						
					}	
					if(palabraCh[i] == 't') {
						if (palabraCh[i+1] == 'h' && palabraCh[i+2] == 'e' && palabraCh[i+3] == 'n') {
							return "Invalido";
						}
					}
				}catch(ArrayIndexOutOfBoundsException ex) {}
				
				
			}
			if (contador!= 1) {
				return "Invalido";
			}
		}
		if(contadorAMUC == 0) {
			return "Invalido";
		}
		
		return "Expresion valida";
	}
	
	
	public String identificador2(String palabra) {

		char[] palabraCh = palabra.toLowerCase().toCharArray();

		if (palabraCh[0] == '.') {
			// Caso donde empiece un punto
			return "Invalido";
		}
		if (palabraCh[0] == '0' && palabraCh.length == 1) {
			// Caso donde solo haya un 0
			return "Numerico";
		}

		// Determinar si es Hexadecimal
		if (palabraCh[0] == '0' && palabraCh[1] == 'x') {
			int contadorHexa;
			if (palabraCh.length == 2) {
				return "Invalido";
			}
			for (int i = 2; i < palabraCh.length; i++) {

				contadorHexa = 0;
				for (int j = 0; j < 16; j++) {
					if (this.validos2[j] == palabraCh[i]) {
						contadorHexa++;
					}
				}
				if (contadorHexa == 0) {
					return "Invalido";
				}
			}
			return "Hexadecimal";
		}

		// Determinar si es Octal
		if (palabraCh[0] == '0' && palabraCh.length > 1) {
			int contadorOct;
			for (int i = 1; i < palabraCh.length; i++) {

				contadorOct = 0;
				for (int j = 0; j < 8; j++) {
					if (this.validos2[j] == palabraCh[i]) {
						contadorOct++;
					}
				}
				if (contadorOct == 0) {
					return "Invalido";
				}
			}
			
			return "Octal";
		}

		// Determinar si es Numerico
		int contadorNum;
		

		for (int i = 0; i < palabraCh.length; i++) {
			contadorNum = 0;
			for (int j = 0; j < 10; j++) {
				if (this.validos2[j] == palabraCh[i]) {
					contadorNum++;

				} else if (palabraCh[i] == '.' && i + 1 != palabraCh.length) {
					/*
					 * Caso de punto flotante donde pueden haber :
					 * 
					 * Numeros enteros Numeros enteros , e (+|-) , numeros enteros
					 */
					int contadorNN;
					for (int k = i+1; k < palabraCh.length; k++) {

						contadorNN = 0;
						for (int l = 0; l < 10; l++) {
							if (this.validos2[l] == palabraCh[k]) {
								contadorNN++; 
							}
							if(palabraCh[k] == 'e' && k+2 <= palabraCh.length) {
								//Se buscara +|- y un numero
								if(palabraCh[k+1] == '+' || palabraCh[k+1] == '-'
								|| palabraCh[k+1] == '0' || palabraCh[k+1] == '1'
								|| palabraCh[k+1] == '2' || palabraCh[k+1] == '3'
								|| palabraCh[k+1] == '4' || palabraCh[k+1] == '5'
								|| palabraCh[k+1] == '6' || palabraCh[k+1] == '7'
								|| palabraCh[k+1] == '8' || palabraCh[k+1] == '9') {
									
									//Se va a buscar numeros enteros solamente
									int ultCont;
									for (int m = k+2; m < palabraCh.length; m++) {

										ultCont = 0;
										for (int n = 0; n < 10; n++) {
											if (this.validos2[n] == palabraCh[m]) {
												ultCont++;
											}
										}
										if (ultCont == 0) {
											return "Invalido";
										}
									}
									return "Punto flotante";
									
								}else {
									return "Invalido";
								}
							} 
						}
						if (contadorNN == 0) {
							return "Invalido";
						}
					}
					return "Punto flotante";
					
				} else if (palabraCh[i] == '.' && i + 1 == palabraCh.length) {
					return "Invalido";
				}
			}
			if (contadorNum == 0) {
				return "Invalido";
			}
		}
		return "Numerico";
	}
	
	public static void main(String [] args) {
		Automata1 a1 = new Automata1();
		
		JOptionPane.showMessageDialog(null, "El siguiente automata decidira si la expresion que sera introducida es valida \n"+
											"si y solo si: \n\n"+
											"- Consisten solo de caracteres alfabéticos, numéricos y el guión bajo (_).\n"+
											"-No pueden comenzar con un carácter numérico\n"+
											"-Deben tener al menos un carácter alfabético.\n"+
											"-No son palabras reservadas (begin, end, else, program, var, if, then)"+
											"\n\nDecidira si la expresion que sera introducida es valida si y solo si: \n"+
											"-Es un numero Octal\n" + "-Es un numero Natural\n"+
											"-Es un numero Hexadecimal\n" + "-Es un numero de Punto Flotante");
		String palabra;
		while(true) {
			palabra = JOptionPane.showInputDialog(null,"Ingrese una expresion que sera evaluada por el automata");
			try {
				JOptionPane.showMessageDialog(null,"El automata ha decidido que la expresion: "+palabra+" es "+a1.identificador(palabra));
			}catch(ArrayIndexOutOfBoundsException aioobe) {
				JOptionPane.showMessageDialog(null,"El automata ha decidido que la expresion: "+palabra+" es no es valida");
			}catch(NullPointerException npe) {
				break;
			}
		}
		
	}
}
