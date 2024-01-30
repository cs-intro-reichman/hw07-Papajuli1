
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1)
			return "";
		else
			return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length() == 0)
			return word2.length();
		if (word2.length() == 0)
			return word1.length();
		if (word1.charAt(0) == word2.charAt(0))
			return levenshtein(tail(word1), tail(word2));
		else {
			int n1 = levenshtein(tail(word1), word2);
			int n2 = levenshtein(word1, tail(word2));
			int n3 = levenshtein(tail(word1), tail(word2));
			int min = Math.min(Math.min(n1, n2), n3);
			return 1 + min;
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++)
			dictionary[i] = in.readLine();
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = word.length();
		word = word.toLowerCase();
		String similar = "";
		for (int i = 1; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) > min) {
				min = levenshtein(word, dictionary[i]);
				similar = dictionary[i];
			}
		}
		if (min > threshold)
			return word;
		else
			return similar;
	}

}
