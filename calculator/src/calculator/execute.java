package calculator;

import java.util.ArrayList;

public class execute {

	
	public String handle(String info) {
		
		
		System.out.println(info);
		String currentnum = new String(); //num currently being constructed
		int minuscount = 0; //count of unary minuses
		boolean inbrack = false; //boolean for wether we are inside a bracket
		int insidebrackcount = 0; //how many brackets are layered inside the first one we encounter
		boolean endbrackfound = false; //have we found the end of the first bracket we encountered
		int dotcount = 0;
		String recursinfo = "";
		String tempmult;
		ArrayList<String> numsarr = new ArrayList<String>();
		ArrayList<String> opsarr = new ArrayList<String>();
		
		
		
		if (info.isEmpty()) {
			return ("");
		}// checks if inputted calc is empty
		
		char last = info.charAt(info.length()-1);
		
		if (!(Character.isDigit(last) || last == '.' || last == ')'  )){
			return ("Syntax Error");
		}//check if last inp is a num, or else syntax error as cannot be computed
		
		
		
		for (int x1 = 0; x1 < info.length(); x1++) {//goes thru string and turns into calculation
			char c1 = info.charAt(x1);//variable of the current character
			
			if(Character.isDigit(c1) || c1 == ('.')) {//if character is a num
				
				if (c1 == '.') {
				    dotcount++;
				    if (dotcount > 1) return "Syntax Error";
				}

				currentnum = currentnum + c1;//add  it to the num creator
			}
			else {	
				
				if (c1 == '-') {//check if unary
					if ((x1 == 0) || (!(Character.isDigit(info.charAt(x1 - 1)) || info.charAt(x1 - 1) == ('.')))) {//is unary
						minuscount++;//if it is add to the unary count
						continue;//go to next iteration
					}
				}//handles unary
				
			
				if (c1 == ')') {
					return "Syntax Error";
				}//if encounter a close bracket with no open bracket
				
				if (c1 == '(' && x1 != 0 && (Character.isDigit(info.charAt(x1 - 1)) || info.charAt(x1 - 1) == ('.'))) {
					
					if (currentnum.equals(".") || currentnum.equals("-.")) {
					    return "Syntax Error";
					}

					inbrack = true;
					tempmult = currentnum;
					numsarr.add(currentnum);
					opsarr.add("*");
					currentnum = "";
					dotcount = 0;
					insidebrackcount++;
					recursinfo = "";
					
					
					for (int x2 = x1 + 1; x2 < info.length(); x2++) {
						char c2 = info.charAt(x2);
						if (c2 == '(') {
							insidebrackcount++;
						}
						else if (c2 == ')') {
							insidebrackcount--;
						}
						
						if (insidebrackcount == 0) {
							
							if (currentnum.equals(".") || currentnum.equals("-.")) {
							    return "Syntax Error";
							}

							numsarr.add(handle(recursinfo));
							
							if (recursinfo.isEmpty()) {
							    return "Syntax Error";
							}

							break;
						}
						
						recursinfo = recursinfo + c2;
						
					}
					
					if (insidebrackcount != 0) {
					    return "Syntax Error";
					}

					
					x1 = x1 + recursinfo.length() + 1;
					continue;

					
				}// if brack encounter, and element before is a digit
				
				else if (c1 == '(' && x1 != 0 && (info.charAt(x1 - 1) == ')')) {
					
					inbrack = true;
					insidebrackcount++;
					opsarr.add("*");
					recursinfo = "";
				
					
					
					for (int x2 = x1 + 1; x2 < info.length(); x2++) {
						char c2 = info.charAt(x2);
						if (c2 == '(') {
							insidebrackcount++;
						}
						else if (c2 == ')') {
							insidebrackcount--;
						}
						
						if (recursinfo.isEmpty()) {
						    return "Syntax Error";
						}

						if (insidebrackcount == 0) {
							
							if (currentnum.equals(".") || currentnum.equals("-.")) {
							    return "Syntax Error";
							}

							numsarr.add(handle(recursinfo));
							
							if (recursinfo.isEmpty()) {
							    return "Syntax Error";
							}

							break;
						}
						
						recursinfo = recursinfo + c2;
						
					}
					
					if (insidebrackcount != 0) {
					    return "Syntax Error";
					}

					x1 = x1 + recursinfo.length() + 1;
					continue;

					
				}// if brack encounter, and element before is another bracket
				
				else if (c1 == '(' && x1 != 0 && !(Character.isDigit(info.charAt(x1 - 1)) || info.charAt(x1 - 1) == ('.'))) {
					inbrack = true;
					insidebrackcount++;
					recursinfo = "";
					
					for (int x2 = x1 + 1; x2 < info.length(); x2++) {
						char c2 = info.charAt(x2);
						if (c2 == '(') {
							insidebrackcount++;
						}
						else if (c2 == ')') {
							insidebrackcount--;
						}
						
						if (insidebrackcount == 0) {
							
							if (currentnum.equals(".") || currentnum.equals("-.")) {
							    return "Syntax Error";
							}

							numsarr.add(handle(recursinfo));
							
							if (recursinfo.isEmpty()) {
							    return "Syntax Error";
							}

							break;
						}
						
						recursinfo = recursinfo + c2;
						
					}
					
					if (insidebrackcount != 0) {
					    return "Syntax Error";
					}

					x1 = x1 + recursinfo.length() + 1;
					continue;

				}//if brack encountered, but element is not a digit
				
				else if (c1 == '(') {
					
					inbrack = true;
					insidebrackcount++;
					recursinfo = "";
					
					
					for (int x2 = x1 + 1; x2 < info.length(); x2++) {
						char c2 = info.charAt(x2);
						if (c2 == '(') {
							insidebrackcount++;
						}
						else if (c2 == ')') {
							insidebrackcount--;
						}
						
						if (insidebrackcount == 0) {
							
							if (currentnum.equals(".") || currentnum.equals("-.")) {
							    return "Syntax Error";
							}

							numsarr.add(handle(recursinfo));
							
							if (recursinfo.isEmpty()) {
							    return "Syntax Error";
							}

							break;
						}
						
						recursinfo = recursinfo + c2;
						
					}
					
					if (insidebrackcount != 0) {
					    return "Syntax Error";
					}

					x1 = x1 + recursinfo.length() + 1;
					continue;

					
				}// if brack encounter, and element before is a digit, at start
				
				
				if (currentnum.isEmpty() && "+*/".indexOf(c1) != -1) {
				    return "Syntax Error";
				}// prevents incorrect stacking of operators
				
				
				
				if (!currentnum.isEmpty()) {
					if ((minuscount % 2 != 0)) {// if unary count is odd then the num is a negative
						currentnum =  '-' + currentnum;
					}
					
					if (currentnum.equals(".") || currentnum.equals("-.")) {
					    return "Syntax Error";
					}

					numsarr.add(currentnum);//add num to array when finished constructing
				}
		
				
				
				currentnum = "";//reset num constructor
				dotcount = 0;
				minuscount = 0;//reset minus count
				if (c1 != '(' && c1 != ')'){
						opsarr.add(ophandlereturn(c1));//add the operation to the ops array
				}
				
				
				
			}	
		}
	
		
		
		if (!currentnum.isEmpty()) {
			
			if ((minuscount % 2 != 0)) {// if unary count is odd then the num is a negative
				currentnum =  '-' + currentnum;
			}
			
			if (currentnum.equals(".") || currentnum.equals("-.")) {
			    return "Syntax Error";
			}//if just a . or -.

			numsarr.add(currentnum);
		}//handles when end of string is reached
		
		
		for (int x2 = 0; x2 < opsarr.size(); x2++) {
			if (opsarr.get(x2).equals("*") || opsarr.get(x2).equals("/")) {
				float firstnum = Float.parseFloat(numsarr.get(x2));
				float secondnum = Float.parseFloat(numsarr.get(x2 + 1));
				String op = opsarr.get(x2);
				String num1 = Float.toString(ophandlecalc(firstnum, secondnum, op));
				opsarr.remove(x2);
				numsarr.remove(x2);
				numsarr.set(x2, num1);
				x2--;
				
			}//converts values to be calculated and plugs into calc subroutine - using BIDMAS
		}//prepares calculation, by BIDMAS order, * and / 
		
		for (int x3 = 0; x3 < opsarr.size(); x3++) {
			float firstnum = Float.parseFloat(numsarr.get(x3));
			float secondnum = Float.parseFloat(numsarr.get(x3 + 1));
			String op = opsarr.get(x3);
			String num1 = Float.toString(ophandlecalc(firstnum, secondnum, op));
			opsarr.remove(x3);
			numsarr.remove(x3);
			numsarr.set(x3, num1);
			x3--;
		}//prepares calculations, by BIDMAS order, + and -
		
			

		return ((numsarr.get(0)));//return final result
		
		
	}
	
		
	
	public String ophandlereturn(char op) {
		switch (op) {
		case '+':
			return "+";
		case '-':
			return "-";
		case '*':
			return "*";
		case '/':
			return "/";
		default: 
			throw new IllegalArgumentException("Invalid Operator");
		}
	
	}//handles converting operation char to string to return
	
	public float ophandlecalc(float firstnum, float secondnum, String op ) {
		switch (op) {
		case "+":
			return (firstnum + secondnum);
		case "-":
			return (firstnum - secondnum);
		case "*":
			return (firstnum * secondnum);
		case "/":
		    if (secondnum == 0) {
		    	return Float.NaN;
		    }
			return (firstnum / secondnum);
		default:
			return (Float) null;
		}
	}//handles physical calculation for * / + -
	

}
