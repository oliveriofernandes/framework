package view.utils;
 import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
   
 public class AllowedCharsPlainDocument extends PlainDocument {  
	private static final long serialVersionUID = 1L;
	final String permitidos;  
   
  public AllowedCharsPlainDocument(String permitidos) {  
  this.permitidos = permitidos;  
  }  
   
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {  
   
  //Aqui é feito o 'filtro', retirando os caracteres não permitidos.  
  //Esta lógica pode ser substituída, para criar qualquer tipo de filtro  
 
	  
//	  if (str == null) return;  
//      
//      String oldString = getText (0, getLength() );  
//      String newString = oldString.substring(0, offs) + str + oldString.substring(offs);   
//                
//      if (newString.length() > 4) {  
//         super.insertString(offs, "", a);  
//      } else {  
//         // Repassa para o pai.  
//         super.insertString(offs, str, a);  
//      }
	  
	  
	  
	  
	  StringBuffer sb = new StringBuffer(str);  
  
  for (int i = sb.length() - 1; i >= 0; i--) {  
	  if ((permitidos.indexOf(sb.charAt(i)) < 0) ) {  
		  sb.replace(i, i + 1, "");
		  
	  }  
  }
  
     
  
  
  super.insertString(offs, sb.toString(), a);  
  
	  
//	if (str == null) return;
//    String oldString = getText (0, getLength() );  
//    String newString = oldString.substring(0, offs) + str + oldString.substring(offs);
//
//    StringBuffer sb = new StringBuffer(str);
//    
//    for (int i = sb.length() - 1; i >= 0; i--) {  
//  	  if ((permitidos.indexOf(sb.charAt(i)) < 0) ) {  
//  		  sb.replace(i, i + 1, "");
//  		if (newString.length() > 4) {  
//    	      super.insertString(offs, "", a);  
//    	   } else {  
//    	      // Repassa para o pai.  
//    	      super.insertString(offs, str, a);  
//    	   }
//  		  
//  	  }  
//    }
//    super.insertString(offs, sb.toString(), a);
    
  
  
  }  
   
  public static void main(String[] args) {  
  JFrame f = new JFrame();  
  f.getContentPane().add(  
  new JTextField(new AllowedCharsPlainDocument("0123456789."), "", 4));  
//		  new JTextField(new TextDocument(4));
		  
		  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  f.pack();  
  f.setVisible(true);  
  }  
 }  