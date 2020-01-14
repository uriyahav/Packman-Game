package gameClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.ourFruit;
import Server.Game_Server;
import Server.game_service;
import gameClient.MyGameGUI;
import oop_dataStructure.OOP_DGraph;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import utils.Point3D;
/**
 * This class represents a simple example for using the GameServer API:
 * the main file performs the following tasks:
 * 1. Creates a game_service [0,23] (line 36)
 * 2. Constructs the graph from JSON String (lines 37-39)
 * 3. Gets the scenario JSON String (lines 40-41)
 * 4. Prints the fruits data (lines 49-50)
 * 5. Add a set of robots (line 52-53) // note: in general a list of robots should be added
 * 6. Starts game (line 57)
 * 7. Main loop (should be a thread) (lines 59-60)
 * 8. move the robot along the current edge (line 74)
 * 9. direct to the next edge (if on a node) (line 87-88)
 * 10. prints the game results (after "game over"): (line 63)
 *  
 * @author boaz.benmoshe
 *
 */
public class SimpleGameClient {
	game_service game;
	
	public static void main(String[] a) {
		test1();}
	public static void test1() {
		//from here to
		int scenario_num = 2;
		game_service game = Game_Server.getServer(scenario_num); // you have [0,23] games
		String g = game.getGraph();
		DGraph gg = new DGraph();
		gg.init(g);
		String info = game.toString();
//		ArrayList<String> fruit =  (ArrayList<String>) game.getFruits();
//		Fruit f = new Fruit(); 
//		for (int i = 0; i < fruit.size(); i++) {
//			f.init(fruit.get(i));
//		}
//		Robot r=new Robot();
//		int num = r.init(game.toString());		
//		Collection<node_data> node = gg.getV();
//		int size = node.size();
//		for (int i = 0; i < num; i++) {
//			int rnd = (int) (Math.random()*size);
//			if (node.contains(rnd)) {
//				Point3D po = gg.getNode(rnd).getLocation();	
//				Robot temp= new Robot(po);
//				r.setHash(rnd,temp);
//				game.addRobot(rnd);
//			}
//		}
		//here
//		Robot r=new Robot();
//		r.init(game.toString());
//		ArrayList<String> Robots= (ArrayList<String>) game.getRobots();
		MyGameGUI GG= new MyGameGUI();
//		JSONObject line;
//		try {
//			line = new JSONObject(info);
//			JSONObject ttt = line.getJSONObject("GameServer");
//			int rs = ttt.getInt("robots");
//			System.out.println(info);
//			System.out.println(g);
//			// the list of fruits should be considered in your solution
//			Iterator<String> f_iter = game.getFruits().iterator();
////			while(f_iter.hasNext()) {System.out.println(f_iter.next());}	
//			int src_node = 0;  // arbitrary node, you should start at one of the fruits
//			for(int a = 0;a<rs;a++) {
//				game.addRobot(src_node+a);
//			}
//		}
//		catch (JSONException e) {e.printStackTrace();}
//		game.startGame();
//		// should be a Thread!!!
//		while(game.isRunning()) {
//			moveRobots(game, gg);
//		}
//		String results = game.toString();
//		System.out.println("Game Over: "+results);
	}
	/** 
	 * Moves each of the robots along the edge, 
	 * in case the robot is on a node the next destination (next edge) is chosen (randomly).
	 * @param game
	 * @param gg
	 * @param log
	 */
	private static void moveRobots(game_service game, graph gg) {
		List<String> log = game.move();
		if(log!=null) {
			long t = game.timeToEnd();
			for(int i=0;i<log.size();i++) {
				String robot_json = log.get(i);
				try {
					JSONObject line = new JSONObject(robot_json);
					JSONObject ttt = line.getJSONObject("Robot");
					int rid = ttt.getInt("id");
					int src = ttt.getInt("src");
					int dest = ttt.getInt("dest");
				
					if(dest==-1) {	
						dest = nextNode(gg, src);
						game.chooseNextEdge(rid, dest);
						System.out.println("Turn to node: "+dest+"  time to end:"+(t/1000));
						System.out.println(ttt);
					}
				} 
				catch (JSONException e) {e.printStackTrace();}
			}
		}
	}
	/**
	 * a very simple random walk implementation!
	 * @param g
	 * @param src
	 * @return
	 */
	private static int nextNode(graph g, int src) {
		int ans = -1;
		Collection<edge_data> ee = g.getE(src);
		Iterator<edge_data> itr = ee.iterator();
		int s = ee.size();
		int r = (int)(Math.random()*s);
		int i=0;
		while(i<r) {itr.next();i++;}
		ans = itr.next().getDest();
		return ans;
	}
	
	
	/*repaint thr fruit*/
	public void GetFruit (game_service game, graph gg) {
		ArrayList<String> fruit =  (ArrayList<String>) game.getFruits();
		ourFruit f = new ourFruit(); 
		for (int i = 0; i < fruit.size(); i++) {
			f.initFruit(fruit.get(i));
		}
		 
		
	}
	
	public void placeRobot (game_service game, graph gg) {
		
		 
		
	}

}

















//package gameClient;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import Server.Game_Server;
//import Server.game_service;
//import dataStructure.DGraph;
//import dataStructure.ourFruit;
//import dataStructure.ourRobots;
//import oop_dataStructure.OOP_DGraph;
//import oop_dataStructure.oop_edge_data;
//import oop_dataStructure.oop_graph;
///**
// * This class represents a simple example for using the GameServer API:
// * the main file performs the following tasks:
// * 1. Creates a game_service [0,23] (line 36)
// * 2. Constructs the graph from JSON String (lines 37-39)
// * 3. Gets the scenario JSON String (lines 40-41)
// * 4. Prints the fruits data (lines 49-50)
// * 5. Add a set of robots (line 52-53) // note: in general a list of robots should be added
// * 6. Starts game (line 57)
// * 7. Main loop (should be a thread) (lines 59-60)
// * 8. move the robot along the current edge (line 74)
// * 9. direct to the next edge (if on a node) (line 87-88)
// * 10. prints the game results (after "game over"): (line 63)
// *  
// * @author boaz.benmoshe
// *
// */
////public class SimpleGameClient {
//public class SimpleGameClient {
//	game_service game;
//
//	public static void main(String[] a) {
//		test1();}
//	public static void test1() {
//		//from here to
//		int scenario_num = 2;
//		game_service game = Game_Server.getServer(scenario_num); // you have [0,23] games
//		String g = game.getGraph();
//		DGraph gg = new DGraph();
//		gg.init(g);
//		String info = game.toString();
//		MyGameGUI gr = new MyGameGUI()
//	}
//	//	public static void main(String[] a) {
//	//		test1();}
//	//	public static void test1() { 
//	//		int scenario_num = 2;
//	//		game_service game = Game_Server.getServer(scenario_num); // you have [0,23] games
//	//		System.out.println(game);
//	//		String g = game.getGraph();
//	//		DGraph gg = new DGraph();
//	//		gg.init(g);
//	//		String bitch= game.getRobots().toString();
//	//		System.out.println(bitch);
//	//		Iterator<String> ad = game.getFruits().iterator();
//	//		ArrayList<ourFruit> of = new ArrayList<ourFruit>();
//	//		while(ad.hasNext()) {
//	//			String temp = ad.next();
//	//			ourFruit c = new ourFruit();
//	//			c.initFruit(temp);
//	//			of.add(c);
//	//		}
//	//		MyGameGUI n = new MyGameGUI(gg);
//	//		ArrayList<ourRobots> rob;
//	//	//	MyGameGUI n = new MyGameGUI(gg);
//	//		String info = game.toString();
//	//		int stre = info.indexOf("\"robots\":");
//	//		//System.out.println(stre);
//	//		String temp2 = info.substring(stre+9, stre+10);
//	//		System.out.println(temp2.toString());
//	//		int roby= Integer.parseInt(temp2);
//	//		System.out.println(roby);
//	//		JSONObject line;
//	//	}
//	//		try {
//	//			line = new JSONObject(info);
//	//			JSONObject ttt = line.getJSONObject("GameServer");
//	//			int rs = ttt.getInt("robots");
//	//			System.out.println(info);
//	//			System.out.println(g);
//	//			// the list of fruits should be considered in your solution
//	//			Iterator<String> f_iter = game.getFruits().iterator();
//	//			while(f_iter.hasNext()) {System.out.println(f_iter.next());}	
//	//			int src_node = 0;  // arbitrary node, you should start at one of the fruits
//	//			for(int a = 0;a<rs;a++) {
//	//				game.addRobot(src_node+a);
//	//			}
//	//		}
//	//		catch (JSONException e) {e.printStackTrace();}
//	//		game.startGame();
//	//		// should be a Thread!!!
//	//		while(game.isRunning()) {
//	//			moveRobots(game, gg);
//	//		}
//	//		String results = game.toString();
//	//		System.out.println("Game Over: "+results);
//	//	}
//	//	/** 
//	//	 * Moves each of the robots along the edge, 
//	//	 * in case the robot is on a node the next destination (next edge) is chosen (randomly).
//	//	 * @param game
//	//	 * @param gg
//	//	 * @param log
//	//	 */
//	//	private static void moveRobots(game_service game, oop_graph gg) {
//	//		List<String> log = game.move();
//	//		if(log!=null) {
//	//			long t = game.timeToEnd();
//	//			for(int i=0;i<log.size();i++) {
//	//				String robot_json = log.get(i);
//	//				try {
//	//					JSONObject line = new JSONObject(robot_json);
//	//					JSONObject ttt = line.getJSONObject("Robot");
//	//					int rid = ttt.getInt("id");
//	//					int src = ttt.getInt("src");
//	//					int dest = ttt.getInt("dest");
//	//				
//	//					if(dest==-1) {	
//	//						dest = nextNode(gg, src);
//	//						game.chooseNextEdge(rid, dest);
//	//						System.out.println("Turn to node: "+dest+"  time to end:"+(t/1000));
//	//						System.out.println(ttt);
//	//					}
//	//				} 
//	//				catch (JSONException e) {e.printStackTrace();}
//	//			}
//	//		}
//	//	}
//	//	/**
//	//	 * a very simple random walk implementation!
//	//	 * @param g
//	//	 * @param src
//	//	 * @return
//	//	 */
//	//	private static int nextNode(oop_graph g, int src) {
//	//		int ans = -1;
//	//		Collection<oop_edge_data> ee = g.getE(src);
//	//		Iterator<oop_edge_data> itr = ee.iterator();
//	//		int s = ee.size();
//	//		int r = (int)(Math.random()*s);
//	//		int i=0;
//	//		while(i<r) {itr.next();i++;}
//	//		ans = itr.next().getDest();
//	//		return ans;
//	//	}
//
//}
