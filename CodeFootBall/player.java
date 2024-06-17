class Player {
    String name;
    String position;
    double quality;

    // Stats
    double MP, G, SOnT, SOffT, BS, OG, A, P, C, Tk, INT, FW, FC, O, YC, RC, GC, PW, S, PS, train;

    public Player(String name, String position, double MP, double G, double SOnT, double SOffT, double BS, double OG,
            double A, double P, double C, double Tk, double INT, double FW, double FC, double O,
            double YC, double RC, double GC, double PW, double S, double PS, double train) {
        this.name = name;
        this.position = position;
        this.MP = MP;
        this.G = G;
        this.SOnT = SOnT;
        this.SOffT = SOffT;
        this.BS = BS;
        this.OG = OG;
        this.A = A;
        this.P = P;
        this.C = C;
        this.Tk = Tk;
        this.INT = INT;
        this.FW = FW;
        this.FC = FC;
        this.O = O;
        this.YC = YC;
        this.RC = RC;
        this.GC = GC;
        this.PW = PW;
        this.S = S;
        this.PS = PS;
        this.train = train;
        calculateQuality();
    }

    private void calculateQuality() {
        this.quality = 0.05 * MP + 10 * G + 4 * SOnT + 2 * SOffT + 2 * BS - 5 * OG
                + 6 * A + 0.2 * P + 0.2 * C + 2 * Tk + 2 * INT + FW - FC - O
                - 2 * YC - 5 * RC - GC + 4 * PW + 5 * S + 5 * PS + 1.5 * train;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', position='" + position + "', quality=" + quality + '}';
    }
}
