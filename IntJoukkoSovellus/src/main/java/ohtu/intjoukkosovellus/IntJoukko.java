
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
		this();
        if (kapasiteetti < 0) {
            return;
        }
        taulukko = new int[kapasiteetti];

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
		this(kapasiteetti);
//        if (kapasiteetti < 0) {
//            throw new IllegalArgumentException("Kapasiteetti ei voi olla negatiivinen.");
//        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("kasavatuskoko ei voi olla negatiivinen");
        }
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % taulukko.length == 0) {
                int [] taulukkoOld = taulukko;
                kopioiTaulukko(taulukko, taulukkoOld);
                taulukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, taulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
		return false;
    }

    public boolean poista(int luku) {
		int kohta = hae(luku);
		siirraVasemmalle(kohta);
		if (kohta >= 0) {
			alkioidenLkm--;
		}
		return (kohta >= 0);
    }

	private void siirraVasemmalle(int kohta) {
		int apu;
		for (int i = kohta; i>=0 && i < alkioidenLkm-1; i++) {
			apu = taulukko[i];
			taulukko[i] = taulukko[i + 1];
			taulukko[i + 1] = apu;
		}
	}

	private int hae(int luku) {
		for (int i = 0; i < taulukko.length; i++) {
			if (taulukko[i] == luku) {
				return i;
			}
		}
		return -1;
	}

	private void kopioiTaulukko(int[] vanha, int[] uusi) {
		System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for (int i=0; i<alkioidenLkm; i++) {
			sb.append(taulukko[i]);
			if (i < alkioidenLkm-1) {
				sb.append(", ");
			}
		}
		return sb.append("}").toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
		for (int i : a.toIntArray()) {
			yhdiste.lisaa(i);
		}
		for (int i : b.toIntArray()) {
			yhdiste.lisaa(i);
		}
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
		for (int i : a.toIntArray()) {
			for (int j : b.toIntArray()) {
				if (i == j) {
					leikkaus.lisaa(j);
				}
			}
		}
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
		for (int i : a.toIntArray()) {
			erotus.lisaa(i);
		}
		for (int j : b.toIntArray()) {
			erotus.poista(j);
		}
        return erotus;
    }
        
}