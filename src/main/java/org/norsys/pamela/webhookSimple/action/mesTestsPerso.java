package org.norsys.pamela.webhookSimple.action;

public class mesTestsPerso {
	
	public static void main(final String[] args) {
		String name = "projects/pamelaapplication/agent/sessions/d0728ec0-ac81-5427-a16a-6dc68ccc0c71/contexts/awaiting_codesecret2";
		//enlever le contexte present
		int longeurChaine = name.length();
		int indexDernierSlash = name.lastIndexOf('/');
		System.out.println( name.substring(0, name.lastIndexOf("/")+1) );
		
		String a = "1eee";
		System.out.println(a.length());
	}
}
