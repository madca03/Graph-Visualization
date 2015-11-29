import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
import java.util.Iterator;

public class DynamicGraph3 {
  private MultiGraph graph;
  private String sourceDGSFile;

  public DynamicGraph3(String sourceDGSFile) {
    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    graph = new MultiGraph("Dynamic Graph");
    graph.display(false);
    graph.addAttribute("ui.antialias");
    graph.addAttribute("ui.stylesheet", "edge { fill-color: grey; }");

    this.sourceDGSFile = sourceDGSFile;

    generateGraph();
  }

  public void generateGraph() {
    while(true) {
      addNodesAndEdges();
      printNodeInfo();
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
          break;
      }
    }
  }

  public void addNodesAndEdges() {
    try {
      /* source -> a component that generates events 
        sink -> a component that receives events

        In this example, the "graph2.dgs" acts as a source of events
        that changes the structure of the graph. Each line in the 
        dgs file is an event.

        The graph object serves as the "sink" or the receiver of the
        events from the "source" dgs file
      */
      FileSource source = FileSourceFactory.sourceFor(sourceDGSFile);
      source.addSink(graph);
      source.begin(sourceDGSFile); // begin reading the file

      while (source.nextStep()) {
        Thread.sleep(200); // pause reading of events from the dgs file
      }
      source.end(); // close the file
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void printNodeInfo() {
    /* iterate over all the nodes in the graph */
    Iterator<? extends Node> nodes = graph.getNodeIterator();

    while (nodes.hasNext()) {
      Node currentNode = nodes.next();

      /* get node's information */
      String nodeName = currentNode.getId();
      int nodeId = (int) currentNode.getNumber("id");
      Object[] nodeSensors = currentNode.getAttribute("sensors");
      Object[] nodeCurrentSensorValues = currentNode.getAttribute("currentSensorValues");
      double nodeBatteryLevel = currentNode.getNumber("batteryLevel");

      /* print node's information */
      System.out.printf("Node %s id: %d\n", nodeName, nodeId);
      System.out.printf("Node %s battery level: %f\n", nodeName, nodeBatteryLevel);
      System.out.printf("Node %s (sensor : value)\n", nodeName);

      for (int i = 0; i < nodeSensors.length; i++) {
        System.out.printf("\t %s : %f\n", nodeSensors[i], nodeCurrentSensorValues[i]);
      }

      System.out.println("");
    }
  }


  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("args: dgs file");
      return;
    }
    DynamicGraph3 dynamicGraph = new DynamicGraph3(args[0]);
  }
}