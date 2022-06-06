package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    private Player player;
    private final Player DEAD_PLAYER = new Player("Fallen", 0);
    private Gun gun;

    @Before
    public void setUp() {
        player = new Player("Victor", 50);
        gun = new Gun("M-47", 10);
        player.addGun(gun);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowExceptionWhenUsernameIsNull() {
        player = new Player(null, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionWhenHealthIsBelowZero() {
        new Player("Victor", -50);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionWhenTheCollectionIsModified() {
        player.getGuns().clear();
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowExceptionWhenAddedGunIsNull() {
        player.addGun(null);
    }

    @Test
    public void testShouldRemoveGun() {
        boolean isRemoved = player.removeGun(gun);
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testShouldReturnGun() {
        Gun gunByName = player.getGun(this.gun.getName());
        Assert.assertEquals(gun, gunByName);
    }

    @Test(expected = IllegalStateException.class)
    public void testShouldThrowExceptionWhenIsDead() {
        DEAD_PLAYER.takeDamage(50);
    }


    @Test
    public void testShouldTakeDamageFromPlayer() {
        player.takeDamage(10);
        Assert.assertEquals(40, player.getHealth());

        player.takeDamage(50);
        boolean zeroHealth = player.getHealth() == 0;
        Assert.assertTrue(zeroHealth);
    }

    @Test
    public void testGetUsername() {
        String name = player.getUsername();
        Assert.assertEquals("Victor", name);
    }
}
