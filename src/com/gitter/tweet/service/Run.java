package com.gitter.tweet.service;


public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
            System.out.println("Usage: java com.gitter.tweet.service.Run [github_user]");
            System.exit(-1);
        }
		
		GitterService gitter = new GitterService(args[0]);
		gitter.execute();		
	}

}
