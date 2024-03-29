public class RankTile extends Tile {
    protected int rank;

    public RankTile(int rank){
        this.rank = rank;
    }

    public boolean matches(Tile other){
        if(super.matches(other)){
            RankTile r = ((RankTile) other);
            return rank == r.rank;
        }
        else{
            return false;
        }
    }

}