/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp;
import java.util.ArrayList;
import java.util.LinkedList;
import htlib.*;
import java.io.IOException;
/**
 *
 * @author mohamed
 */
public class buildweb {
    String[] page_title;
    int Numberofpages;
    
    ArrayList <String> fullpages=new ArrayList <>();
    LinkedList <Integer> pageindex=new LinkedList <>();
    LinkedList <Integer> heading=new LinkedList <>();//parent id  for h
    LinkedList <Integer> p=new LinkedList <>();// parent id for p
    LinkedList <Integer> a=new LinkedList <>();// parent id for a
    LinkedList <Integer> img=new LinkedList <>();// parent id for img
    LinkedList <String> link=new LinkedList <>();// parent id for link
    LinkedList <Integer> ta=new LinkedList <>();// parent id for textarea
    LinkedList <Integer> tb=new LinkedList <>();// parent id for textbox
    LinkedList <Integer> button=new LinkedList <>();// parent id for button
    LinkedList <Integer> ul=new LinkedList <>();// parent id for list
    LinkedList <Integer> li=new LinkedList <>();// parent id for list item

    public void setFullpages(ArrayList<String> fullpages) {
        this.fullpages = fullpages;
    }

    public void setPageindex(LinkedList<Integer> pageindex) {
        this.pageindex = pageindex;
    }

    public ArrayList<String> getFullpages() {
        return fullpages;
    }

    public LinkedList<Integer> getPageindex() {
        return pageindex;
    }

    public void setNumberofpages(int Numberofpages) {
        this.Numberofpages = Numberofpages;
    }

    public int getNumberofpages() {
        return Numberofpages;
    }

    public void setPage_title(String[] page_title) {
        this.page_title = page_title;
    }

    public String[] getPage_title() {
        return page_title;
    }

    
    
     public void createheading(int n)
    {
      heading.add(n);
    }
     public void createparagraph(int n)
    {
      p.add(n);
    }

    public void createanchor(int n) {
        a.add(n);
       }
    public void createimage(int n) {
        img.add(n);
       }
    void createtb(int i) {
        tb.add(i);
    }

    void createta(int i) {
        ta.add(i);
     }

    void createul(int i) {
        ul.add(i);
    }

    void createbutton(int i) {
        button.add(i);
    }
    
    
    

    public void createlink(int page,int a,String l) {
        
        link.add(a, l);// need edit
         }
    
   public Website build_pages(Website x)
    {
        for (int i = 0; i < Numberofpages; i++) {
            x.add_page(new Page(page_title[i],i));
        }

        return x;
    } 
   public Website build_headers(Website x)
    {
        
        for (int i = 0; i < heading.size(); i++) {
          //  System.err.println("h"+pageindex.get(heading.get(i)));
            H tempH= new H("hello","BLUE","NEWTIMESROMAN",1,i);
            Page tempp;
            tempp=x.get_page(pageindex.get(heading.get(i)));
            x.remove_page(pageindex.get(heading.get(i)));
             
            tempp.add_element(tempH);
            x.add_page(tempp);
    } 
        return x;
    }
    public Website build_p(Website x)
    {
        
        for (int i = 0; i < p.size(); i++) {
            //System.err.println("p"+pageindex.get(p.get(i)));
            P tempP= new P("hello","BLUE","NEWTIMESROMAN",i);
            Page tempp;
            tempp=x.get_page(pageindex.get(p.get(i)));
             x.remove_page(pageindex.get(p.get(i)));
             
             tempp.add_element(tempP);
              x.add_page(tempp);
    } 
        return x;
    }
    
    public Website build_a(Website x)
    {
        
        for (int i = 0; i < a.size(); i++) {
         //   System.err.println(pageindex.get(a.get(i)));
//            for (int j = 0; j < link.size(); j++)
//            {
//            A tempA= new A(link.get(j), "CLICK ME!",i);
//            
//            Page tempp;
//            tempp=x.get_page(pageindex.get(a.get(i)));
//             x.remove_page(pageindex.get(a.get(i)));
//             
//             tempp.add_element(tempA);
//              x.add_page(tempp);
//            }
            
            A tempA= new A("dd", "CLICK ME!",i);
            
            Page tempp;
            tempp=x.get_page(pageindex.get(a.get(i)));
             x.remove_page(pageindex.get(a.get(i)));
             
             tempp.add_element(tempA);
              x.add_page(tempp);
            
          
    } 
        return x;
    }
    
     public Website build_img(Website x)
    {
        
        for (int i = 0; i < img.size(); i++) {
            //System.err.println(pageindex.get(img.get(i)));
            Img imageone = new Img("image.jpg",i);
            Page tempp;
            tempp=x.get_page(pageindex.get(img.get(i)));
             x.remove_page(pageindex.get(img.get(i)));
            
             tempp.add_element(imageone);
              x.add_page(tempp);
          
    } 
        return x;
    }
     public Website build_button(Website x)
    {
        
        for (int i = 0; i < button.size(); i++) {
            //System.err.println(pageindex.get(img.get(i)));
            Button button1 = new Button("button", "CLICK THIS BUTTON", i);
            Page tempp;
            tempp=x.get_page(pageindex.get(button.get(i)));
             x.remove_page(pageindex.get(button.get(i)));
            
             tempp.add_element(button1);
              x.add_page(tempp);
          
    } 
        return x;
    }
     public Website build_tb(Website x)
    {
        
        for (int i = 0; i < tb.size(); i++) {
            //System.err.println(pageindex.get(img.get(i)));
            Input tb1 = new Input("text", "CLICK",i);
            Page tempp;
            tempp=x.get_page(pageindex.get(tb.get(i)));
             x.remove_page(pageindex.get(tb.get(i)));
            
             tempp.add_element(tb1);
              x.add_page(tempp);
            System.err.println("dds");
    } 
        return x;
    }
    public Website build_ta(Website x)
    {
        
        for (int i = 0; i < ta.size(); i++) {
            //System.err.println(pageindex.get(img.get(i)));
            TextArea ta1 = new TextArea(2,2,"heloo","hellow",i);
            Page tempp;
            tempp=x.get_page(pageindex.get(ta.get(i)));
             x.remove_page(pageindex.get(ta.get(i)));
            
             tempp.add_element(ta1);
              x.add_page(tempp);
          
    } 
        return x;
    }
    public Website build_UL(Website x)
    {
        
        for (int i = 0; i < ul.size(); i++) {
            //System.err.println(pageindex.get(img.get(i)));
            UL ul1 = new UL("disc", "compact",i);
            Page tempp;
            tempp=x.get_page(pageindex.get(ul.get(i)));
             x.remove_page(pageindex.get(ul.get(i)));
            
             tempp.add_element(ul1);
              x.add_page(tempp);
          
    } 
        return x;
    }
//    public Website build_LI(Website x)
//    {
//        
//        for (int i = 0; i < li.size(); i++) {
//            //System.err.println(pageindex.get(img.get(i)));
//            LI li1 = new LI(0, "circle", "First element", i);
//            Page tempp;
//            tempp=x.get_page(pageindex.get(img.get(i)));
//             x.remove_page(pageindex.get(img.get(i)));
//            
//             tempp.add_element(li1);
//              x.add_page(tempp);
//          
//    } 
//        return x;
//    }
     
   
    public void build_website() throws IOException
     {
          Website x = new Website("/home/mohamed/Desktop");
          Website y=build_pages(x);
          Website z=build_headers(y);
          Website q=build_p(z);
          Website v=build_a(q);
          Website w=build_img(v);
          Website o=build_button(w);
          Website xx=build_ta(o);
          Website yy=build_tb(xx);
          Builder b = new Builder();
         b.build(yy);
     }

    
   
}
