
import java.awt.*;
import java.util.*;

/**
 * The <code>Heap</code> class provides the utilities to draw a complete
 * binary tree on the corresponding graphical context. It also contains
 * some animation methods used in <b>heap sort</b>, <b>priority queue</b>
 * and <b>sorting using priority queue</b>
 * <p>
 * There are basically 4 types of graphical objects: <b>input</b>,
 * <b>output</b>, <b>tree representation of heap</b>, and
 * <b>array representation of heap</b>.
 * The <code>Node</code> class is used to store the graphical objects.
 * @see Node
 */
public class Heap extends Node {

    Font bigFont, smallFont, tinyFont, hugeFont, fixFont;
    DrawingPanel drawingPanel;
    Node tree, posn;
    Vector nodeList, posnList, inputList, outputList, heapArray;
    int max_node_per_level, depth;
    Node movingNode;
    boolean exchanging;
    Point[] aPoints;
    Color darkgreen = new Color(0, 140, 0);
    Color darkRed = new Color(140, 0, 0);
    Color darkBlue = new Color(0, 0, 140);
    ComBox runningCom;
    int max_size;

    /**
     * Create a new heap and pre-calculate the location of each node
     * given the maximum number of node passed in as the parameter.
     * @param drawingPanel The drawing panel where the heap is to be drawn.
     * @param max_size The maximum number of nodes in the heap.
     */
    public Heap(DrawingPanel drawingPanel, int max_size) {
	this.bigFont = drawingPanel.bigFont;
	this.smallFont = drawingPanel.smallFont;
	this.tinyFont = drawingPanel.tinyFont;
	this.hugeFont = drawingPanel.hugeFont;
	this.fixFont = drawingPanel.fixFont;
	this.drawingPanel = drawingPanel;
	this.max_size = max_size;
	this.tree = null;
	nodeList = new Vector();
	posnList = new Vector();
	inputList = new Vector();
	heapArray = new Vector();
	outputList = new Vector();
	calNodesCoord(max_size);
	hideMovingNode();
	exchanging = false;
	runningCom = null;
    }

    /**
     * Repaints the drawing panel and delay.
     */
    public void redraw() {
	drawingPanel.repaint();
	drawingPanel.delay();
    }

    /**
     * Re-initialize the heap. This method is called when the animation
     * is re-started.
     */
    public void initHeap() {
	this.tree = null;
	nodeList = new Vector();
	inputList = new Vector();
	heapArray = new Vector();
	outputList = new Vector();
	exchanging = false;
    }

    /**
     * Set the input objects based on the array of weight given by the 
     * parameter.
     * @param a Array of weights for the input objects
     */
    public void setInput(int[] a) {
	inputList = new Vector();
	for (int i = 0; i < a.length; i++) {
	    Node node = new Node(a[i]);
	    node.x = drawingPanel.offset + 30 + (i+1)*22;
	    node.y = bottomMostPosn(posn) + 85;
	    inputList.addElement(node);
	}
	redraw();
    }

    /**
     * Adds an input object with the weight specified by the parameter.
     * @param i Weight of the additional input object.
     */
    public void addInput(int i) {
	Node node = new Node(i);
	node.x = drawingPanel.panel_width - drawingPanel.offset - 50;
	node.y = bottomMostPosn(posn) + 50;
	inputList.addElement(node);
	runningCom = new ComBox(node.x - 120, node.y + 50,
		"input arriving...", Color.blue, Color.yellow, fixFont);
	redraw();
    }

    /**
     * Remove the first input object.
     */
    public void delFirstInput() {
	inputList.removeElementAt(0);
    }

    /**
     * Remove an object from each of the tree and array representation of
     * the heap and adds an object to the output object list.
     * <p>
     * This method involves an animation which merge the root of the heap
     * tree and the first object of the heap array and move the resultant
     * object to the output position.
     * @param i The weight of the output object added.
     */
    public void addOutput(int i) {
	Node node = new Node(i);
	node.y = drawingPanel.offset;
	node.x = drawingPanel.offset + 80 + (outputList.size())*22;

	runningCom = new ComBox(150, node.y + 20,
		"Extracting output...", Color.blue, Color.yellow, fixFont);

	movingNode = new Node(-1);
	int srcX = movingNode.x = drawingPanel.offset + 20;
	int srcY = movingNode.y = ((Node)posnList.firstElement()).y;
	if (!heapArray.isEmpty()) {
	    Node heapNode;
	    if (!nodeList.isEmpty())
		heapNode = (Node)nodeList.firstElement();
	    else {
		heapNode = (Node)posnList.firstElement();
		movingNode.setWeight(i);
		movingNode.x = heapNode.x;
		movingNode.y = heapNode.y;
	    }

	    Node arrayNode = (Node)heapArray.firstElement();

	    if (!nodeList.isEmpty()) {
	    	heapNode.setRightNode(null); 
		heapNode.setLeftNode(null);
	    }
	    int srcHx = heapNode.x;
	    int srcAy = arrayNode.y;
	    for (int k = 0; k < 5; k++) {
		if (!nodeList.isEmpty())
		    heapNode.x += (srcX - srcHx)/5;
		else
		    movingNode.x += (srcX - srcHx)/5;
		arrayNode.y += (srcY - srcAy)/5;
		redraw();
	    }
	}
	
	if (!nodeList.isEmpty())
	    nodeList.removeElementAt(0);
	if (!heapArray.isEmpty())
	    heapArray.removeElementAt(0);
	movingNode.setWeight(i);
	movingNode.x = srcX;
	movingNode.y = srcY;
	redraw();
	int destX = node.x;
	int destY = node.y;
	for (int k = 0; k < 3; k++) {
	    movingNode.x += (destX - srcX)/3;
	    redraw();
	}
	movingNode.x = destX;
	for (int k = 0; k < 5; k++) {
	    movingNode.y += (destY - srcY)/5;
	    redraw();
	}
	hideMovingNode();

	runningCom = null;
	outputList.addElement(node);
	redraw();
    }

    /**
     * Move the last object of the heap to the first/root (for both
     * tree and array representation).  The movement is also animated.
     */
    public void moveLast2First() {
	movingNode = (Node)nodeList.lastElement();
	runningCom = new ComBox(movingNode.x + 30, movingNode.y,
		"Moving last node to root...", Color.blue, Color.yellow,
		fixFont);
	if (nodeList.size() > 2) {
	    //remove this node from parent
	    int p = parent(nodeList.size()+1);
	    if ((nodeList.size()+1) == right(p))
	        ((Node)nodeList.elementAt(p-2)).setRightNode(null);
	    else
	        ((Node)nodeList.elementAt(p-2)).setLeftNode(null);
	}

	//remove this node
	nodeList.removeElementAt(nodeList.size()-1);

	//lastHeapArray
	Node lastHeapArray = (Node)heapArray.lastElement();
	

	// move node down;
	if (!nodeList.isEmpty()) {
	    movingNode.y += 17; lastHeapArray.y += 17; redraw();
	    movingNode.y += 17; lastHeapArray.y += 17; redraw();
	}

	int destHeapArrayX = drawingPanel.offset + 20;
	int srcHeapArrayX = lastHeapArray.x;
	int destX = drawingPanel.offset + 25;
	int srcX = movingNode.x;
	for (int i = 0; i < 5; i++) {
	    movingNode.x += (destX - srcX)/5;
	    lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    redraw();
	}
	movingNode.x = destX;
	int destY = ((Node)posnList.firstElement()).y;
	int srcY = movingNode.y;
	for (int i = 0; i < 5; i++) {
	destY = ((Node)posnList.firstElement()).y;
	    movingNode.y += (destY - srcY)/5;
	    lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    redraw();
	}
	movingNode.y = destY;
	destX = ((Node)posnList.firstElement()).x;
	srcX = movingNode.x;
	for (int i = 0; i < 5; i++) {
	    movingNode.x += (destX - srcX)/5;
	    lastHeapArray.x += (destHeapArrayX - srcHeapArrayX)/15; 
	    redraw();
	}
	movingNode.x = destX;
	lastHeapArray.x = destHeapArrayX;
	if (!nodeList.isEmpty()) {
	    lastHeapArray.y -= 17; redraw();
	    lastHeapArray.y -= 17;
	}
	heapArray.removeElementAt(heapArray.size()-1);
	heapArray.insertElementAt(lastHeapArray, 0);

	nodeList.insertElementAt(movingNode, 0);
	if (nodeList.size() > 1)
	    movingNode.setLeftNode((Node)nodeList.elementAt(1));
	if (nodeList.size() > 2)
	    movingNode.setRightNode((Node)nodeList.elementAt(2));
	
	hideMovingNode();
	runningCom = null;
	redraw();
    }

    /**
     * Restore/remove highlight for the node in the heap (both
     * tree and array representation.
     * @param i The node to be restore.
     */
    public void restore(int i) {
	if (i < nodeList.size()) {
	    ((Node)nodeList.elementAt(i)).highlight = false;
	    ((Node)heapArray.elementAt(i)).highlight = false;
	}
    }

    /**
     * Highlight a node on the heap tree and its correspondence on the
     * array representation.
     * @param i The node to be highlighted.
     */
    public void highlight(int i) {
	if (i < nodeList.size()) {
	    ((Node)nodeList.elementAt(i)).highlight = true;
	    ((Node)heapArray.elementAt(i)).highlight = true;
	}
    }

    private void hideMovingNode() {
	movingNode = new Node(-1);
    }

    /**
     * Shows arrow from node i to node j and the other way round (animation)
     * to indicate that these two nodes are going to be exchanged.
     * @param i Parent node to be swapped.
     * @param j Child node to be swapped.
     */
    public void exchangeArrow(int i, int j) {
	// pre-condition: i+1 is parent, j+1 is child
	Node child = (Node)nodeList.elementAt(j);
	Node parent = (Node)nodeList.elementAt(i);

	exchanging = true;

	runningCom = new ComBox(child.x + 20, child.y - 10,
		"Swapping...", Color.blue, Color.yellow, fixFont);

	// check if is left or right child
	if ((j+1)==left(i+1)) {
	    // assign points: Point[] aPoints 
	    aPoints = new Point[2];
	    aPoints[0] = new Point(child.x + 2, child.y);
	    aPoints[1] = new Point(child.x + 2, parent.y + 15);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(child.x + 2, child.y);
	    aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    aPoints[2] = new Point(parent.x - 8, parent.y + 10);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(child.x + 2, child.y);
	    aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    aPoints[2] = new Point(parent.x, parent.y + 10);
	    redraw();
	    aPoints = new Point[2];
	    aPoints[0] = new Point(parent.x, parent.y + 10);
	    aPoints[1] = new Point(child.x + 6, parent.y + 10);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(parent.x, parent.y + 10);
	    aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    aPoints[2] = new Point(child.x + 2, parent.y + 25);
	    redraw();
	    aPoints[0] = new Point(parent.x, parent.y + 10);
	    aPoints[1] = new Point(child.x + 2, parent.y + 10);
	    aPoints[2] = new Point(child.x + 2, child.y);
	    redraw();
	} else { // right child
	    aPoints = new Point[2];
	    aPoints[0] = new Point(child.x + 17, child.y);
	    aPoints[1] = new Point(child.x + 17, parent.y + 15);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(child.x + 17, child.y);
	    aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    aPoints[2] = new Point(parent.x + 25, parent.y + 10);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(child.x + 17, child.y);
	    aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    aPoints[2] = new Point(parent.x + 20, parent.y + 10);
	    redraw();
	    aPoints = new Point[2];
	    aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    aPoints[1] = new Point(child.x + 12, parent.y + 10);
	    redraw();
	    aPoints = new Point[3];
	    aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    aPoints[2] = new Point(child.x + 17, child.y - 10);
	    redraw();
	    aPoints[0] = new Point(parent.x + 20, parent.y + 10);
	    aPoints[1] = new Point(child.x + 17, parent.y + 10);
	    aPoints[2] = new Point(child.x + 17, child.y);
	    redraw();
	}

	Node array1 = (Node)heapArray.elementAt(i);
	Node array2 = (Node)heapArray.elementAt(j);
	Point orig1 = new Point(array1.x, array1.y);
	Point orig2 = new Point(array2.x, array2.y);
	boolean adj = (Math.abs(i-j) == 1);
	if (!adj) array1.y +=17; array2.y -= 17; redraw();
	if (!adj) array1.y +=17; array2.y -= 17; redraw();

	for (int k = 0; k < 4; k++) {
	    array1.x += (orig2.x - orig1.x)/4;
	    array2.x += (orig1.x - orig2.x)/4;
	    redraw();
	}
	array1.x = orig2.x;
	array2.x = orig1.x;
	if (!adj) array1.y -=17; array2.y += 17; redraw();

	int tmpWeight = array1.getWeight();
	array1.setWeight(array2.getWeight());
	array2.setWeight(tmpWeight);
	array1.x = orig1.x; array1.y = orig1.y;
	array2.x = orig2.x; array2.y = orig2.y;
	runningCom = null;
	redraw();

	exchanging = false;
    }

    /**
     * Remove the first object from the input object list and animate its
     * movement to the heap, i.e. it splits into two, one to the array
     * representation and the other to the tree representation of the heap.
     */
    public void input2heap() {
	int inputNum = inputList.size();
	    Node node = (Node)inputList.firstElement();
	    delFirstInput();
	    movingNode = node;
	    movingNode.y -= 17; redraw();

	    Node newNode = new Node(movingNode.getWeight());
	    newNode.x = drawingPanel.offset + 20 + heapArray.size() * 22;
	    movingNode.y = bottomMostPosn(posn) + 50;
	    redraw();
	runningCom = new ComBox(newNode.x + 100, node.y,
		"Inserting input into heap...", Color.blue, Color.yellow, 
		fixFont);

	    Node arrayNode = new Node(movingNode.getWeight());
	    arrayNode.x = movingNode.x;
	    arrayNode.y = movingNode.y;
	    heapArray.addElement(arrayNode);

	    Node destNode = (Node)posnList.elementAt(nodeList.size());
	    int destX = destNode.x;
	    int destY = destNode.y;
	    int sourceX = movingNode.x;

	    movingNode.y -= 20; redraw();
	    movingNode.y -= 20; redraw();

	    for (int l = 0; l < 5; l++) {
		movingNode.x += (destX - sourceX)/5;
		arrayNode.x += (newNode.x - sourceX)/5;
	    	redraw();
	    }
	    movingNode.x = destX;
	    arrayNode.x = newNode.x;

	    redraw();

	    int srcY = node.y;
	    for (int k = 0; k < 5; k++) {
		movingNode.y += (destY - srcY)/5;
		redraw();
	    }
	    hideMovingNode();
	    node.x = destX; node.y = destY;
	    insert(node);
	    runningCom = null;
	    redraw();
    }

    /**
     * Re-assign the heap using the array of weights passed in as the
     * parameter.
     * @param a Array of weight for the newly re-assigned heap.
     */
    public void setHeap(int[] a) {
	if (a.length > posnList.size())
	    calNodesCoord(a.length);
	tree = null;
	nodeList = new Vector();
	for (int i = 0; i < a.length; i++)
	    insert(new Node(a[i]));
    } // setData()

    private void insert(Node node) {
	nodeList.addElement(node);
	if (posnList.size() >= nodeList.size()) {
	    node.x = ((Node)posnList.elementAt(nodeList.size()-1)).x;
	    node.y = ((Node)posnList.elementAt(nodeList.size()-1)).y;
	}
	if (tree == null) {
	    tree = node;
	} else {
	    // locate blank branch to link to this node
	    for (int i = 0; i < nodeList.size()-1; i++) {
		Node n = (Node)nodeList.elementAt(i);
		if (n.getLeftNode() == null) {
		    n.setLeftNode(node);
		    break;
		} else if (n.getRightNode() == null) {
		    n.setRightNode(node);
		    break;
	   	}
	    }
	}
    }

    /**
     * Returns the number of nodes in the heap.
     * @return The heap size.
     */
    public int heapSize() {
	return nodeList.size();
    }

    /**
     * Re-assign the weight of node i according to the parameters.
     * @param i The node, which weight is going to be re-assigned.
     * @param val The new weight to be set.
     */
    public void setNode(int i, int val) {
	((Node)nodeList.elementAt(i)).setWeight(val);
    }

    private void calNodesCoord(int size) {
	// count max number of nodes needed
	int i =  0; int sum = power(2, i++);
	while (sum < size)
	    sum += power(2, i++);
	max_node_per_level = power(2, i-1);
	depth = i-1;

	int max_nodes = sum;
	// insert nodes with no value into position tree
	Node backTree = tree; // backup current tree
	Vector backNodeList = nodeList; nodeList = new Vector(); 
	posnList = new Vector();
	for (int j = 0; j < max_nodes; j++) {
	    Node node = new Node(-1);
	    insert(node);
	}
	posn = tree;
	posnList = nodeList;
	tree = backTree;
	nodeList = backNodeList;
	

	// assign Y
	int curDepth = 0; sum = power(2, 0);
	int offset = drawingPanel.offset + 30;
	//int yStart = drawingPanel.panel_height/2 -100 - depth*20;
	int yStart = 100;
	for (int j = 0; j < posnList.size(); j++) {
	    Node node = (Node)posnList.elementAt(j);

	    if ( (j+1) > sum) {
		curDepth++; sum += power(2, curDepth);
	    }
	    node.depth = curDepth;

	    node.y = yStart + curDepth*40;

	    // assign X for bottom level
	    if (curDepth == depth) {
		node.x = 22 + offset;
		offset = node.x;
	    }
	}

	// assign remaining X
	for (int j = posnList.size() - 1; j >= 0; j--) {
	    Node node = (Node)posnList.elementAt(j);

	    if (node.depth == depth)
		continue;

	    if (node.getLeftNode() != null && node.depth == depth-1) {
		node.x = node.getLeftNode().x + 12;
	    } else if (node.getLeftNode() != null && 
		node.getRightNode() != null) {
		node.x = (node.getLeftNode().x + node.getRightNode().x)/2;
	    } else {
		// both children null -> can only at depth - 1
		int l = left(j+1);
	    }
	}
    }

    /* ------------------------ Utils -------------------- */

    /**
     * Performs the exponent of <code>num</code> to the power of 
     * <code>pow</code>.
     * @param num Base.
     * @param pow Exponent.
     */
    public int power(int num, int pow) {
	int result = 1;
	for (int i = 0; i < pow; i++)
	    result *= num;
	return result;
    }

    /**
     * @return The position of the parent of node i.
     * @param i The node, whose parent is of interest.
     */
    public int parent(int i) {
	return (i/2);
    }

    /**
     * Returns the left child of node i.
     */
    public int left(int i) {
        return (2*i);
    }

    /**
     * Returns the right child of node i.
     */
    public int right(int i) {
        return (2*i + 1);
    }
 
    /* ------------------------ Graphical Primitives ------------------ */

    /**
     * Draws all graphical objects within this class.
     * @param g Graphical context.
     */
    public void drawTree(Graphics g) {
	for (int i = 0; i < inputList.size(); i++) {
	    drawLeafNode(g, (Node)inputList.elementAt(i));
	}
	// draw input box
	if (inputList.size() > 0)
	    new ComBox( ((Node)inputList.lastElement()).x + 50,
			bottomMostPosn(posn) + 85, "Input",
		Color.white, darkBlue, fixFont ).draw(g);


	for (int i = 0; i < heapSize(); i++) {
	    Node node = (Node)nodeList.elementAt(i);
	    drawNode(g, node);
	}

	for (int i = 0; i < outputList.size(); i++)
	    drawLeafNode(g, (Node)outputList.elementAt(i));

	if (movingNode.getWeight() > -1)
	    drawNode(g, movingNode);

        for (int i = 0; i < heapArray.size(); i++) {
            Node node = (Node)heapArray.elementAt(i);
            drawArrayNode(g, node.x, node.y, node);
        }

	if (exchanging)
	    drawArrow(g, aPoints);

	// draw tree box
	new ComBox( ((Node)posnList.lastElement()).x + 40,
		((Node)posnList.lastElement()).y - 40, 
		"tree representation of the same heap",
		Color.white, darkRed, fixFont ).draw(g);

	// draw array box
	if (inputList.size() == 0)
	    new ComBox( max_size*22 + drawingPanel.offset + 30,
		bottomMostPosn(posn) + 50,
		"array representation of the heap",
		Color.white, darkRed, fixFont ).draw(g);

	// draw output box
	new ComBox( drawingPanel.offset,
		10, "Output",
		Color.white, darkBlue, fixFont ).draw(g);

	if (runningCom != null)
	    runningCom.draw(g);
    }

    /**
     * Draw an arrowed line segment using the array of points.
     * @param g Graphical context
     * @param points The vortices of the line segment. 
     * An arrow head will be drawn using the last point in the array at the tip.
     */
    public void drawArrow(Graphics g, Point[] points) {
	// remember: no drawPolyline in jdk1.0.*
	int[] axPoints = new int[3], ayPoints = new int[3];
	g.setColor( Color.magenta );
	if (points.length < 3) {
	  Point endArrow = points[1], startArrow = points[0];
	  if (endArrow.y == startArrow.y) {
	    g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    axPoints[0] = endArrow.x; ayPoints[0] = endArrow.y;
	    int unit = (endArrow.x - startArrow.x)/
			Math.abs(endArrow.x - startArrow.x);
	    axPoints[1] = endArrow.x - unit*2;
	    ayPoints[1] = endArrow.y - 2;
	    axPoints[2] = endArrow.x - unit*2;
	    ayPoints[2] = endArrow.y + 2;
	    g.fillPolygon(axPoints, ayPoints, 3);
	  } else if (endArrow.x == startArrow.x) {
	    g.drawLine(startArrow.x, startArrow.y, endArrow.x, endArrow.y);
	    axPoints[0] = endArrow.x; ayPoints[0] = endArrow.y;
	    int unit = (endArrow.y - startArrow.y)/
			Math.abs(endArrow.y - startArrow.y);
	    axPoints[1] = endArrow.x - 1;
	    ayPoints[1] = endArrow.y - unit*2;
	    axPoints[2] = endArrow.x + 2;
	    ayPoints[2] = endArrow.y - unit*2;
	    g.fillPolygon(axPoints, ayPoints, 3);
	  }
	} else if (points.length == 3) {
	    // draw a line for the first and second points then use
	    // drawArrow to draw the remaining segment
	    g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
	    Point[] line = new Point[2];
	    line[0] = points[1];
	    line[1] = points[2];
	    drawArrow(g, line);
	}
    }

    /**
     * @param node The root node of the tree
     * @return The width of the tree in the number of pixels.
     */
    public int treeWidth(Node node) {
        return (rightMostPosn(node) - leftMostPosn(node));
    }
 
    /**
     * @param node The root node of the tree
     * @return The left most position of the tree
     */
    public int leftMostPosn(Node node) {
        if (node.isLeaf())
            return node.x;
        else
            return leftMostPosn(node.getLeftNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The right most position of the tree
     */
    public int rightMostPosn(Node node) {
        if (node.isLeaf())
            return (node.x + 20);
        else
            return rightMostPosn(node.getRightNode());
    }
 
    /**
     * @param node The root node of the tree
     * @return The height of the tree in the number of pixels.
     */
    public int treeHeight(Node node) {
        return (bottomMostPosn(node) - node.y);
    }
 
    /**
     * @param node The root node of the tree
     * @return The bottom most position of the tree
     */
    public int bottomMostPosn(Node node) {
        if (node.isLeaf())
            return (node.y + 30);
        else {
            int rightBottom = bottomMostPosn(node.getRightNode());
            int leftBottom = bottomMostPosn(node.getLeftNode());
            return (rightBottom > leftBottom ? rightBottom : leftBottom);
        }
    }

    /**
     * Drawing a leaf node of the heap.
     * @param g Graphical context
     * @param node The node to be drawn
     */
    public void drawLeafNode(Graphics g, Node node) {
        int x = node.x; int y = node.y;
        int weight = node.getWeight();
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.blue );
        g.fillRect(x, y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(x, y, 20, 30);
        g.setFont(hugeFont);
        g.setColor( Color.yellow );
        g.drawString(""+weight, x+2, y+25);
    }

    /**
     * Draw a node of the array representation of the heap.
     * @param g Graphical context
     * @param x The x coordinate of the top left corner of the node
     * @param y The y coordinate of the top left corner of the node
     * @param node The node to be drawn
     */
    public void drawArrayNode(Graphics g, int x, int y, Node node) {
        int weight = node.getWeight();
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( Color.red );
        g.fillRect(x, y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(x, y, 20, 30);
        g.setFont(hugeFont);
        g.setColor( Color.yellow );
        g.drawString(""+weight, x+2, y+25);
    }
 
    /**
     * Drawing a node based on the input parameters.
     * @param g Graphical context
     * @param node The node to be drawn
     */
    public void drawNode(Graphics g, Node node) {
        if (node.highlight)
            g.setColor(Color.black);
        else
            g.setColor( darkgreen );
        g.fillRect(node.x, node.y, 20, 30);
	g.setColor(Color.black);
	g.drawRect(node.x, node.y, 20, 30);
        g.setColor( Color.white );
	g.setFont( hugeFont );
        g.drawString(""+node.getWeight(), node.x + 2, node.y+25);
 
        // draw links to children
	if (node.getLeftNode() != null) {
            g.setColor( Color.black );
            g.drawLine(node.x + 6, node.y + 30,
                node.getLeftNode().x + 10, node.getLeftNode().y);
	    if (node.highlightLeft) {
                g.drawLine(node.x + 5, node.y + 30,
                    node.getLeftNode().x + 9, node.getLeftNode().y);
                g.drawLine(node.x + 4, node.y + 30,
                    node.getLeftNode().x + 8, node.getLeftNode().y);
	    }
	}

	if (node.getRightNode() != null) {
            g.drawLine(node.x + 14, node.y + 30,
                node.getRightNode().x + 10, node.getRightNode().y);
	    if (node.highlightRight) {
                g.drawLine(node.x + 15, node.y + 30,
                    node.getRightNode().x + 11, node.getRightNode().y);
                g.drawLine(node.x + 16, node.y + 30,
                    node.getRightNode().x + 12, node.getRightNode().y);
	    }
	}
    }

    /**
     * Move the tree starting with node dx pixels to the right and dy
     * pixels down.
     * @param node The root node of the tree to be moved.
     * @param dx The change in x direction.
     * @param dy The change in y direction.
     */
    public void moveNode(Node node, int dx, int dy) {
        node.x += dx;
        node.y += dy;
        if (!node.isLeaf()) {
            moveNode(node.getLeftNode(), dx, dy);
            moveNode(node.getRightNode(), dx, dy);
        }
    }
}
