import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.Iterator;

public class StaticGraph2 {
  private MultiGraph graph;

  public StaticGraph2() {
    /* set the graph renderer */
    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

    /* instantiate a multigraph - capable of having bidirectional edges between two nodes */
    graph = new MultiGraph("Graph");
    graph.addAttribute("ui.quality");
    graph.addAttribute("ui.antialias");

    /* add CSS styles */
    graph.addAttribute("ui.stylesheet", 
      "edge { fill-color: grey; }");

    /* display the graph */
    graph.display(false);

    generateGraph();
  }

  public void generateGraph() {
    addNodes();
    addEdges();
    setNodeAttributes();
    printNodeInfo();
  }

  public void addNodes() {
    /* add nodes;
      1st parameter: name of node
    */
    Node nodeA = graph.addNode("A");
    Node nodeB = graph.addNode("B");
    Node nodeC = graph.addNode("C");

    /* set nodes' position in space */
    nodeA.setAttribute("xy", 0, 1);
    nodeB.setAttribute("xy", 1, 1);
    nodeC.setAttribute("xy", -1, -1);
  }

  public void addEdges() {
    /* add edges;
      1st parameter: name of edge
      2nd parameter: source node
      3rd parameter: target node
      4th parameter: classifies the edge as either directed (boolean = true) or undirected (boolean = false) */
    Edge ab = graph.addEdge("A_B", "A", "B", true);
    Edge bc = graph.addEdge("B_C", "B", "C", true);
    Edge ca = graph.addEdge("C_A", "C", "A", true);
    Edge ba = graph.addEdge("B_A", "B", "A", true);
    Edge cb = graph.addEdge("C_B", "C", "B", true);
    Edge ac = graph.addEdge("A_C", "A", "C", true);

    /* set label of the edge to be displayed on the graph */
    ab.setAttribute("ui.label", "AB");
    bc.setAttribute("ui.label", "BC");
    ca.setAttribute("ui.label", "CA");
    ba.setAttribute("ui.label", "BA");
    cb.setAttribute("ui.label", "CB");
    ac.setAttribute("ui.label", "AC");
  }

  public void setNodeAttributes() {
    /* Iterate over all the nodes in the graph*/
    Iterator<? extends Node> nodes = graph.getNodeIterator();
    int i = 1;

    while (nodes.hasNext()) { 
      Node currentNode = nodes.next();
      int multiplier = 100;

      /* We can also add other attributes to a node/edge like node sensor type, node sensor values, etc. */
      currentNode.setAttribute("id", i++);
      currentNode.setAttribute("sensors", "Humidity", "Temperature"); // array of sensors
      currentNode.setAttribute("currentSensorValues", Math.random() * multiplier, Math.random() * multiplier); // array of sensor values
      currentNode.setAttribute("batteryLevel", Math.random() * multiplier);
    }
  }

  public void printNodeInfo() {
    Iterator<? extends Node> nodes = graph.getNodeIterator();

    while (nodes.hasNext()) {
      Node currentNode = nodes.next();

      /* We can also access the nodes' attributes. */
      String nodeName = currentNode.getId();
      int nodeId = (int) currentNode.getNumber("id");
      Object[] nodeSensors = currentNode.getAttribute("sensors");
      Object[] nodeCurrentSensorValues = currentNode.getAttribute("currentSensorValues");
      double nodeBatteryLevel = currentNode.getNumber("batteryLevel");

      /* Print node's information */
      System.out.printf("Node %s id: %d\n", nodeName, nodeId);
      System.out.printf("Node %s battery level: %f\n", nodeName, nodeBatteryLevel);
      System.out.printf("Node %s (sensor : currentValue)\n", nodeName);

      for (int i = 0; i < nodeSensors.length; i++) {
        System.out.printf("\t %s : %f \n", nodeSensors[i], nodeCurrentSensorValues[i]);
      }
      System.out.println("");
    }
  }

  public static void main(String[] args) {
    StaticGraph2 staticGraph = new StaticGraph2();
  }
}