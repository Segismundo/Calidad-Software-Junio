package marcosYedro.Android.SpaceInvaders;

public class SelectorVelocidad {
    public boolean esVelocidadValida(int i)
    {
        if (i >= 1 && i<= 5)
        {
            return true;
        }
        return false;
    }
}
