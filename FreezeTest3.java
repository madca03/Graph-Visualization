import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.util.MouseManager;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import java.util.Random;
import java.lang.System;
import java.lang.Thread;
import java.awt.event.MouseEvent;


public class FreezeTest3 {
    public static void main(String argv[]) throws Exception {
        System.setProperty("org.graphstream.ui.renderer",
                        "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        MultiGraph g = new MultiGraph("test");
        g.setAttribute("ui.stylesheet", "edge { fill-color: grey; }");
        
        Viewer viewer = g.display(false);
        viewer.getDefaultView().setMouseManager(new MouseManager() {
                @Override
                 public void mouseClicked(MouseEvent e) { System.out.println("mouseClicked"); }
                 @Override
                 public void mousePressed(MouseEvent e) { System.out.println("mousePressed"); }
                 @Override
                 public void mouseReleased(MouseEvent e) { System.out.println("mouseReleased"); }
                 @Override
                 public void mouseEntered(MouseEvent e) { System.out.println("mouseEntered"); }
                 @Override
                 public void mouseExited(MouseEvent e) { System.out.println("mouseExited"); }
                 @Override
                 public void mouseDragged(MouseEvent e) { System.out.println("mouseDragged"); }
                 @Override
                 public void mouseMoved(MouseEvent e) { System.out.println("mouseMoved"); }
                 @Override
                 public void init(GraphicGraph graph, View view) { System.out.println("Hello"); }
                 @Override
                 public void release() {}
        });

        g.addAttribute("ui.antialias");
        g.addAttribute("ui.quality");
        g.addNode("0");
        g.addNode("1");
        g.addNode("2");
        g.addNode("3");
        g.addNode("4");

        g.addEdge("0_0", "0", "0", true).addAttribute("layout.weight", 1.227542);
        g.addEdge("0_1", "0", "1", true).addAttribute("layout.weight", 2.800382);
        g.addEdge("0_2", "0", "2", true).addAttribute("layout.weight", 1.555942);
        g.addEdge("0_3", "0", "3", true).addAttribute("layout.weight", 1.458835);
        g.addEdge("0_4", "0", "4", true).addAttribute("layout.weight", 5.358586);
        g.addEdge("1_0", "1", "0", true).addAttribute("layout.weight", 34.739941);
        g.addEdge("1_1", "1", "1", true).addAttribute("layout.weight", 6.793453);
        g.addEdge("1_2", "1", "2", true).addAttribute("layout.weight", 4.321183);
        g.addEdge("1_3", "1", "3", true).addAttribute("layout.weight", 4.722595);
        g.addEdge("1_4", "1", "4", true).addAttribute("layout.weight", 3.855663);
        g.addEdge("2_0", "2", "0", true).addAttribute("layout.weight", 3.940225);
        g.addEdge("2_1", "2", "1", true).addAttribute("layout.weight", 6.686229);
        g.addEdge("2_2", "2", "2", true).addAttribute("layout.weight", 3.365270);
        g.addEdge("2_3", "2", "3", true).addAttribute("layout.weight", 3.579588);
        g.addEdge("2_4", "2", "4", true).addAttribute("layout.weight", 12.345065);
        g.addEdge("3_0", "3", "0", true).addAttribute("layout.weight", 1.000000);
        g.addEdge("3_1", "3", "1", true).addAttribute("layout.weight", 8.582073);
        g.addEdge("3_2", "3", "2", true).addAttribute("layout.weight", 26.649030);
        g.addEdge("3_3", "3", "3", true).addAttribute("layout.weight", 4.708364);
        g.addEdge("3_4", "3", "4", true).addAttribute("layout.weight", 39.450249);
        g.addEdge("4_0", "4", "0", true).addAttribute("layout.weight", 14.329541);
        g.addEdge("4_1", "4", "1", true).addAttribute("layout.weight", 13.686543);
        g.addEdge("4_2", "4", "2", true).addAttribute("layout.weight", 24.973300);
        g.addEdge("4_3", "4", "3", true).addAttribute("layout.weight", 4.874955);
        g.addEdge("4_4", "4", "4", true).addAttribute("layout.weight", 7.091967);


        g.getNode("0").addAttribute("layout.frozen");
        g.getNode("0").addAttribute("xy", 0.0, -1.0);
        g.getNode("1").addAttribute("layout.frozen");
        g.getNode("1").addAttribute("xy", 0.0, 1.0);
        g.getNode("2").addAttribute("layout.frozen");
        g.getNode("2").addAttribute("xy", 10.0, -1.0);
        g.getNode("3").addAttribute("layout.frozen");
        g.getNode("3").addAttribute("xy", 10.0, 1.0);
        g.getNode("4").addAttribute("layout.frozen");
        g.getNode("4").addAttribute("xy", -1.0, 0.0);

        // SpringBox layout = new SpringBox(false, new Random(0));
        // viewer.enableAutoLayout();
    }
}