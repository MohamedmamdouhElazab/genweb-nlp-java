/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mohamed
 */
public class NewJFrame {
       public NewJFrame() throws IOException {
       run(2);    
         System.out.println("nlp work");
       open();
    }
    public void run(int x)
    {
    String command = null;
    if(x==1)
    {
         command = "./cmd"; //without adding our nlp
    }
     if(x==3)
    {
         command = "./cmd2"; // with adding our nlp
    }
      if(x==2)
    {
         command = "./cmd2"; // with adding our nlp
    }
      
       
       Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Read the output
        BufferedReader reader =  
              new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        try {
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try { 
            proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
    
    buildweb b=new buildweb();
        ArrayList <String> ner=new ArrayList <>();
        ArrayList <String> word=new ArrayList <>();
        ArrayList <String> normalizedNER=new ArrayList <>();
        ArrayList <String> count=new ArrayList <>();
        ArrayList <String> fullpages=new ArrayList <>();
        LinkedList <Integer> index=new LinkedList <>();
        LinkedList <String> Description=new LinkedList <>();
        LinkedList <Integer> Descriptioncount=new LinkedList <>();
        int N,flag=0,k=0,f=0,K=1,header=0,p=0,a=0,img=0,sumofd=0,dd=0,ll=0; //f for page
        String []name;
        String app = ""; 
        
     public void read2() throws FileNotFoundException, IOException, ParseException
    {
         try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("input.txt.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray data = (JSONArray)jsonObject.get("sentences");
            for(Object o: data){
                JSONObject jsonObject1 = (JSONObject) o;
                JSONArray data1 = (JSONArray)jsonObject1.get("tokens");
             for(Object o1: data1){
                JSONObject jsonObject2 = (JSONObject) o1;
                String word1 = (String)jsonObject2.get("word");
                String ner1 = (String)jsonObject2.get("ner");
                String normalize1 = (String)jsonObject2.get("normalizedNER");
                word.add(word1);
                ner.add(ner1);
                if(normalize1==null)
                {
                    normalizedNER.add("0");
                }
                else
                {
                    normalizedNER.add(normalize1);
                }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void Read()
    { 
        try {
            
            read2();
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public void getnumberofpages()
    {
         // number of pages
        for(int i=0;i<word.size();i++)
        {
            
            if("NOofpages".equals(ner.get(i)))
            {
                if((!"0".equals(normalizedNER.get(i))))
                {
                N=(int)Double.parseDouble(normalizedNER.get(i));
                b.setNumberofpages(N);
                }
            }
        }
        name=new String [b.getNumberofpages()];
    }
    public void open() throws IOException
    {
        Read();
        getnumberofpages();
        
        for(int j=0;j<word.size();j++)
        {
          // names of pages 
            if("pagename".equals(ner.get(j)))
            { 
                if(("a".equals(word.get(j)))||("A".equals(word.get(j)))||("the".equals(word.get(j)))||("The".equals(word.get(j))))
                {
                    // do nothing
                }
                else
                {
                app+=word.get(j)+" ";  
                }
              if("page".equals(word.get(j).toLowerCase())) // need to be updated 
              {
                  // for unrepeated pages names
                  if(k<b.getNumberofpages())
                  {
                   for(int i=0;i<b.getNumberofpages();i++)
                   {
                       // ignore repeated page names put flag=1
                       if(name[i] == null ? app == null : name[i].equals(app))
                       {
                           flag=1;
                       }
                   }
                   // add unrepeated pages names
                   if(flag==0)
                   {
                    name[k]=app;
                    k++;
                   }
                  }
                  // for all pages name
                  f++;
                  fullpages.add(app);
                  app="";
              }
            }
            // structure of pages
                if("header".equals(ner.get(j)))
                {
                    for(int i=0;i<K;i++)
                    {
                    b.createheading(f-1);// index of the parent
                    }
                    header++;
                    K=1;
                }
                if("P".equals(ner.get(j).toUpperCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                    b.createparagraph(f-1);// index of the parent
                    }
                     p++;
                     K=1;
                }
                if("a".equals(ner.get(j).toLowerCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                    b.createanchor(f-1);// index of the parent
                    }
                     a++;// still in progress
                     K=1;
                }
                if("img".equals(ner.get(j).toLowerCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                    b.createimage(f-1);// index of the parent
                    }
                     img++;
                     K=1;
                }
                if("button".equals(ner.get(j).toLowerCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                    b.createbutton(f-1);// index of the parent
                    }
                    // button++;
                     K=1;
                }
                if("ul".equals(ner.get(j).toLowerCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                    b.createul(f-1);// index of the parent
                    }
                     //ul++;
                     K=1;
                }
                if("li".equals(ner.get(j).toLowerCase()))
                {
                     for(int i=0;i<K;i++)
                    {
                   // b.createli(f-1,ul-1);// index of the parent
                    }
                     
                     K=1;
                }
                if("text".equals(ner.get(j).toLowerCase()))
                {
                   ll=1; 
                }
                if("textbox".equals(ner.get(j).toLowerCase()))
                {
                    if(ll==1)
                    {
                     for(int i=0;i<K;i++)
                    {System.err.println("ddd"+f);
                    b.createtb(f-1);// index of the parent
                    }
                     //tb++;
                     K=1;
                    }
                    ll=0;
                }
                if("textarea".equals(ner.get(j).toLowerCase()))
                {
                    if(ll==1)
                    {
                     for(int i=0;i<K;i++)
                    {
                    b.createta(f-1);// index of the parent
                    }
                     //ta++;
                     K=1;
                    }
                    ll=0;
                }
                if("link".equals(ner.get(j).toLowerCase()))
                {
                   b.createlink(f-1,a-1,word.get(j)); // still in progress
                }
                if("text".equals(ner.get(j).toLowerCase()))
                {
                    // still in progress
                }
                if("color".equals(ner.get(j).toLowerCase()))
                {
                    // still in progress
                }
                if("font".equals(ner.get(j).toLowerCase()))
                {
                    // still in progress
                }
                
                if("NUMBER".equals(ner.get(j)))
                {
                    if((!"0".equals(normalizedNER.get(j))))
                    {
                      K=(int)Double.parseDouble(normalizedNER.get(j));           
                    }
                }
        }
        // for creating index which we will use to create object of page
         for(int i=0;i<fullpages.size();i++)
         {
              index.add(i, -1);
         }
          for(int i=0;i<name.length;i++)
         {
             for(int j=0;j<fullpages.size();j++)
             {
              if(name[i] == null ? fullpages.get(j) == null : name[i].equals(fullpages.get(j)))
               {
                index.remove(j);
                index.add(j, i);
               }
             }
         }
          b.setPage_title(name);
          b.setFullpages(fullpages);
          b.setPageindex(index); 
          b.build_website();      
          Description();//for now
    }
    public void Description()
    {
        
        Read();
        getnumberofpages();
        System.out.println(word);
         System.out.println(ner);
         System.out.println(normalizedNER);
        for(int j=0;j<word.size();j++)
        {
            if("description".equals(normalizedNER.get(j)))
            {
                
                
                for(int i=0;i<Description.size();i++)
                {
                    if(ner.get(j) == null ? Description.get(i) == null : ner.get(j).equals(Description.get(i)))
                    {
                        dd=Descriptioncount.get(i);
                        dd++;
                         System.out.println(dd);
                        Descriptioncount.remove(i);
                        Descriptioncount.add(i,dd);
                         }
                    else{
                       Description.add(ner.get(j)); 
                    }
                }
                
                if(dd==0)
                {
                Description.add(ner.get(j));
                Descriptioncount.add(1);
                }
               // System.out.println("ner"+j+ner.get(j)+" "+word.get(j));
                //1) counter 2)linked list to carry the field name and number of repeate
            }
        }
        for(int i=0;i<Descriptioncount.size();i++)
                {
                 sumofd+=Descriptioncount.get(i);
                }
        for(int i=0;i<Descriptioncount.size();i++)
                {
                    System.out.println("description "+Description.get(i)+" "+(Descriptioncount.get(i)/sumofd)*100+"%");
                }
                
    
         
                  
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        //System.setProperty("java.awt.headless", "true"); 
        System.err.println("yes we did it ");
     NewJFrame n= new NewJFrame();
    }
}
