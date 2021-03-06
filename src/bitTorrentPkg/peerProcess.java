package bitTorrentPkg;

import java.io.IOException;
import java.util.*;

/* peerProcess.java
 * Authors: Joey Siracusa, Marcus Ball, Anurag Komaravelli
 * 
 * This process is launched on each machine.  It reads Common.cfg and PeerInfo.cfg, initializes
 * the related variables, and has peers connect and share with each other. 
 * 
 */


public class peerProcess {
	
	
	public static void main(String[] args) throws IOException {		
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a peer ID:");
		int peerID = input.nextInt();
		System.out.println(peerID);
		Host host = new Host(peerID);
		System.out.println(host.isFirstPeer());

		if(peer.isFirstPeer()){
			
		}
		*/
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a peer ID:");
		int peerID = input.nextInt();
		Logger.setPeerID(peerID);
		Logger.testLog();
		Tools.debug("[PeerProcess.main] My peer ID: %d",peerID);
		
		NeighborController.setHost(new Host(peerID));
		NeighborController.init();
		NeighborController.host.init();
		
		Tools.debug("[PeerProcess.main] %s.",(NeighborController.host.isFirstPeer())?"Is first peer":"Is not first peer");
		
		ServerEdge server = new ServerEdge();
		server.start();
		if(!NeighborController.host.isFirstPeer()){
			NeighborController.host.initiateTCPConnections(); //else, initiate tcp connections with previous peers
		}
		
	}

}
