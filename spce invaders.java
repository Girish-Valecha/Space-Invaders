import org.apache.commons.lang3.RandomUtils;

public class SpaceInvadersScene extends Phaser.Scene {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private PlayerShip player;
    private Group enemies;
    private Group bullets;

    public SpaceInvadersScene() {
        super("SpaceInvadersScene");
    }

    @Override
    public void create() {
        // Create the player spaceship
        player = new PlayerShip(this, 400, 550);

        // Create a group of enemy spaceships
        enemies = this.physics.add.group();
        for (int i = 0; i < 10; i++) {
            enemies.add(new EnemySpaceship(this, i * 80 + 40, 50));
        }

        // Create a group of bullets
        bullets = this.physics.add.group();

        // Set up physics
        this.physics.world.setBounds(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void update(int time, delta) {
        // Handle player movement
        player.update();

        // Handle enemy movement
        enemies.children.each(enemy -> enemy.update());

        // Handle bullet collisions
        this.physics.overlap(bullets, enemies, this.bulletHitEnemy, null, this);
    }

    private void bulletHitEnemy(Bullet bullet, EnemySpaceship enemy) {
        bullet.destroy();
        enemy.destroy();
    }
}