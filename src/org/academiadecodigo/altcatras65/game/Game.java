package org.academiadecodigo.altcatras65.game;

import org.academiadecodigo.altcatras65.util.CollisionDetector;
import org.academiadecodigo.altcatras65.util.GameSprites;
import org.academiadecodigo.altcatras65.util.Sound;
import org.academiadecodigo.altcatras65.game_objects.GameObject;
import org.academiadecodigo.altcatras65.game_objects.alive.enemy.Enemy;
import org.academiadecodigo.altcatras65.game_objects.alive.enemy.EnemySpawner;
import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.game_objects.props.perks.variants.Fridge;
import org.academiadecodigo.altcatras65.game_objects.props.perks.PerkFactory;
import org.academiadecodigo.altcatras65.game_objects.props.perks.variants.MasterCoder;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.Mouse;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.Projectile;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.ProjectileType;
import org.academiadecodigo.altcatras65.game_objects.props.stateless.Wall;
import org.academiadecodigo.altcatras65.game_objects.props.stateless.WallFactory;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;
import org.academiadecodigo.altcatras65.simple_grafics.SGGrid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    public static LinkedList<Projectile> projectiles;
    private static final int NEXT_ROUND_DELAY = 1500;
    private boolean fridgeWasConsumed;
    private boolean fridgeNotEnoughScore;
    private boolean mariWasConsumed;
    private boolean mariNotEnoughScore;
    private LinkedList<GameObject> objects;
    private LinkedList<Picture> hearts;
    private LinkedList<Picture> armor;
    private Player player;
    private SGGrid grid;
    private CollisionDetector collisionDetector;
    private int delay;
    private int zombiesToSpawn;
    private int roundsCounter;
    private Text roundsLabel;
    private Text playerScoreLabel;
    private Sound nextRoundsound;
    private Sound bottleSound;
    private Sound masterCoderSound;
    private Sound deadZombieSound;
    private Sound masterCoderInteracted;
    private boolean masterCoderCreated;


    public Game(int cols, int rows, int delay) throws IOException, UnsupportedAudioFileException {
        this.delay = delay;
        this.projectiles = new LinkedList<>();
        this.hearts = new LinkedList<>();
        this.armor = new LinkedList<>();
        this.grid = new SGGrid(cols, rows);
        this.roundsCounter = 1;
        this.zombiesToSpawn = 2;
        this.roundsLabel = new Text(grid.getWidth() / 2, 30, "Round: " + roundsCounter);
        this.playerScoreLabel = new Text(grid.getWidth() - 100, 30, "Score: " + 0);
        this.player = new Player(100, 0, ProjectileType.MOUSE, grid.makeGridPosition(18, 11, GameSprites.PLAYER), grid);
        //Audio
        this.nextRoundsound = new Sound("/resources/audio/next-round-sound.wav");
        this.bottleSound = new Sound("/resources/audio/bottle.wav");
        this.deadZombieSound = new Sound("/resources/audio/zombie-growl-sound.wav");
        this.masterCoderSound = new Sound("/resources/audio/master-coder-appears.wav");
        this.masterCoderInteracted = new Sound("/resources/audio/ukelele-acord.wav");
    }

    public void init() {
        this.grid.init();
        this.roundsLabel.setColor(Color.WHITE);
        this.roundsLabel.draw();
        this.playerScoreLabel.setColor(Color.WHITE);
        this.playerScoreLabel.draw();
        this.objects = new LinkedList<>();
        this.collisionDetector = new CollisionDetector(objects);

        //Create player
        this.objects.add(this.player);
        this.player.setCollisionDetector(collisionDetector);

        //Create the fridge
        this.objects.add(PerkFactory.createFridge(grid));
        this.objects.getLast().setCollisionDetector(collisionDetector);


        createWalls();
        createEnemies();
        decideHeartsCounter();
        decideArmorCounter();
    }

    private void createEnemies() {
        for (int i = 0; i < zombiesToSpawn; i++) {
            objects.add(EnemySpawner.spawnEnemy(grid));
            int indexOfLast = objects.indexOf(objects.getLast());
            objects.get(indexOfLast).setCollisionDetector(collisionDetector);
        }
    }

    private void createWalls() {
        //create walls
        objects.add(WallFactory.createWall(grid, 0, 1, 28 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 0, 1, grid.getCellSize(), 19 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 0, 4, 18 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 0, 5, 18 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 27, 1, grid.getCellSize(), 19 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 0, 19, 28 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 9, 15, grid.getCellSize(), 4 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 10, 15, 4 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 1, 12, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 1, 13, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 5, 13, 11 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 5, 12, 11 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 1, 8, grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 1, 9, grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 3, 7, 12 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 3, 8, 12 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 15, 7, grid.getCellSize(), 4 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 17, 5, grid.getCellSize(), 4 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 18, 7, grid.getCellSize(), 2 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 15, 15, grid.getCellSize(), 4 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 18, 13, grid.getCellSize(), 6 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 18, 13, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 18, 14, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 18, 13, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 23, 14, 4 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 23, 13, 4 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 23, 3, 4 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 23, 4, 4 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 23, 5, grid.getCellSize(), 7 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 20, 7, grid.getCellSize(), 5 * grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 20, 7, 3 * grid.getCellSize(), grid.getCellSize()));
        objects.add(WallFactory.createWall(grid, 20, 8, 3 * grid.getCellSize(), grid.getCellSize()));


        for (GameObject object : objects) {
            if (object instanceof Wall) {
                object.setCollisionDetector(this.collisionDetector);
            }
        }
    }

    public void start() {
        this.player.getPos().hide();
        this.player.getPos().show();

        int currentPlayerHp = 0;
        int currentPlayerArmor = 0;
        int currentPlayerScore = 0;

        currentPlayerHp = player.getHp();
        currentPlayerArmor = player.getArmor();
        currentPlayerScore = player.getScore();


        //Move everything in the game
        while (player.isAlive()) {

            moveAllZombies();
            moveAllProjectiles();

            //check the cooldown so it can decrease
            if (player.getShootCooldown() > 0) {
                player.setShootCooldown(player.getShootCooldown() - 1);
            }

            if (currentPlayerHp != player.getHp()) {
                currentPlayerHp = player.getHp();
                decideHeartsCounter();
            }

            if (currentPlayerArmor != player.getArmor()) {
                currentPlayerArmor = player.getArmor();
                decideArmorCounter();
            }

            if (currentPlayerScore != player.getScore()) {
                currentPlayerScore = player.getScore();
                updatePlayerScoreLabel(currentPlayerScore);
            }

            //If the player consumed fridge show beer on screen
            if (fridgeWasConsumed) {
                fridgeWasConsumed = false;
                showImage(GameSprites.BEER_MENU.getGetSpritePath());
            }

            //If the player cant consume
            if (fridgeNotEnoughScore) {
                fridgeNotEnoughScore = false;
                showImage(GameSprites.NOT_ENOUGH_SCORE.getGetSpritePath());
            }

            if (mariWasConsumed) {
                mariWasConsumed = false;
                mariNotEnoughScore = false;
                showImage(GameSprites.MASTER_CODER_UKELELE.getGetSpritePath());
            }

            //If the player cant consume
            if (mariNotEnoughScore) {
                mariNotEnoughScore = false;
                showImage(GameSprites.NOT_ENOUGH_SCORE_MASTER_CODER.getGetSpritePath());
            }


            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Check zombies alive to change round
            if (checkZombiesAlive() == 0) {

                //Show next round announcement
                Picture nextRound = new Picture(grid.PADDING, grid.PADDING, GameSprites.NEXTROUND.getGetSpritePath());
                nextRound.draw();
                changeRound();

                try {
                    this.nextRoundsound.play(true);
                    Thread.sleep(NEXT_ROUND_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                nextRound.delete();
            }

            //At round 3 create mari master coder
            if (roundsCounter == 3 && !masterCoderCreated) {
                createMasterCoderMari();
            }
        }


        //Deletes all hearts when dead
        decideHeartsCounter();
        decideArmorCounter();

        Picture gameOver = new Picture(0, 0, GameSprites.GAMEOVER.getGetSpritePath());
        gameOver.draw();
    }

    private void updatePlayerScoreLabel(int score) {
        this.playerScoreLabel.setText("Score: " + score);
    }

    private void changeRound() {

        //Clean the enemies of the list
        Iterator<GameObject> it = objects.iterator();
        while (it.hasNext()) {
            GameObject gameObject = it.next();
            if (gameObject instanceof Enemy) {
                it.remove();
            }
        }

        roundsCounter++;
        zombiesToSpawn += 3;
        System.out.println("Zombies Spawned: " + zombiesToSpawn);
        createEnemies();
        this.roundsLabel.setText("Round: " + roundsCounter);
    }

    /**
     * Move all zombies on screen
     */
    private void moveAllZombies() {
        for (GameObject object : objects) {
            GridDirectionType direction;
            if (object instanceof Enemy) {
                if (((Enemy) object).isAlive()) {
                    direction = ((Enemy) object).chooseDirection();
                    ((Enemy) object).move(direction);
                }
            }
        }

    }

    /**
     * Return how many zombies are alive
     *
     * @return
     */
    private int checkZombiesAlive() {
        int zombiesAliveCounter = 0;
        for (GameObject object : objects) {
            if (object instanceof Enemy) {
                Enemy enemy = ((Enemy) object);
                zombiesAliveCounter = enemy.isAlive() ? zombiesAliveCounter + 1 : zombiesAliveCounter;
            }
        }
        return zombiesAliveCounter;
    }

    /**
     * Make all projectiles move in a direction
     */
    private void moveAllProjectiles() {
        for (Projectile projectile : projectiles) {

            if (projectile instanceof Mouse) {

                //Move the mouse in the direction the player is
                Mouse mouse = ((Mouse) projectile);

                for (int i = 0; i < ProjectileType.MOUSE.getTravelDistance(); i++) {
                    mouse.mThrow(i, mouse.getDirectionType());

                    //check if the projectile is in the position of another object
                    for (GameObject gameObject : objects) {
                        if (mouse.getPos().getCol() == gameObject.getPos().getCol() && mouse.getPos().getRow() == gameObject.getPos().getRow()) {
                            if (gameObject instanceof Wall) {
                                mouse.getPos().hide();
                                break;
                            }

                            if (gameObject instanceof Enemy) {
                                this.deadZombieSound.play(true);
                                Enemy enemy = ((Enemy) gameObject);
                                enemy.hit(mouse.getDamage());
                                player.setScore(player.getScore() + enemy.getScoreValue());
                                mouse.getPos().hide();
                                break;
                            }

                            if (gameObject instanceof Player) {
                                //Do nothing
                                continue;
                            }
                        }
                    }

                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                mouse.setLifeSpan(mouse.getlifeSpan() - 500);

                //When the lifespan ends destroy the bullet
                if (mouse.getlifeSpan() == 0) {
                    mouse.getPos().hide();
                    this.projectiles.remove(mouse);
                }

            }
        }
    }

    /**
     * Create the life hearts on the screen
     */
    private void createLifeHearts(int numberHearts) {
        int x = 30;
        int y = 27;

        for (int i = 0; i < numberHearts; i++) {
            hearts.add(new Picture(x, y, GameSprites.HEART.getGetSpritePath()));
            hearts.get(i).draw();
            x += 30;
        }
    }

    /**
     * Method that decides how many hearts to draw
     */
    private void decideHeartsCounter() {
        //delete the hearts
        for (Picture heart : hearts) {
            heart.delete();
        }

        int playerHp = player.getHp();

        //call the method to redraw the hearts
        switch (playerHp) {
            case 20:
                createLifeHearts(1);
                break;
            case 40:
                createLifeHearts(2);
                break;
            case 60:
                createLifeHearts(3);
                break;
            case 80:
                createLifeHearts(4);
                break;
            case 100:
                createLifeHearts(5);
                break;

        }
    }

    private void decideArmorCounter() {
        //delete the hearts
        for (Picture a : armor) {
            a.delete();
        }

        int playerArmor = player.getArmor();

        //call the method to redraw the hearts
        switch (playerArmor) {
            case 20:
                createArmorUI(1);
                break;
            case 40:
                createArmorUI(2);
                break;
            case 60:
                createArmorUI(3);
                break;
            case 80:
                createArmorUI(4);
                break;
            case 100:
                createArmorUI(5);
                break;

        }
    }

    private void createArmorUI(int numberArmor) {
        int x = 180;
        int y = 27;

        for (int i = 0; i < numberArmor; i++) {
            armor.add(new Picture(x, y, GameSprites.UKELELE.getGetSpritePath()));
            armor.get(i).draw();
            x += 30;
        }
    }

    private void showImage(String path) {
        Picture picture = new Picture(0, 0, path);
        picture.draw();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        picture.delete();

    }

    public void checkConsume() {
        for (GameObject object : objects) {
            if (object instanceof Fridge) {
                if (object.getPos().getCol() == this.player.getPos().getCol() &&
                        object.getPos().getRow() == this.player.getPos().getRow() - 2) {
                    boolean consumed = ((Fridge) object).consume(this.player);
                    this.bottleSound.play(true);

                    if (consumed) {
                        this.fridgeWasConsumed = true;
                        this.fridgeNotEnoughScore = false;
                    } else {
                        this.fridgeNotEnoughScore = true;
                        this.fridgeWasConsumed = false;
                    }
                }

            }
            if (object instanceof MasterCoder) {
                if (object.getPos().getCol() == this.player.getPos().getCol() - 1 &&
                        object.getPos().getRow() == this.player.getPos().getRow()) {
                    boolean consumed = ((MasterCoder) object).consume(this.player);
                    this.masterCoderInteracted.play(true);

                    if (consumed) {
                        this.mariWasConsumed = true;
                        this.mariNotEnoughScore = false;
                    } else {
                        this.mariNotEnoughScore = true;
                        this.mariWasConsumed = false;
                    }
                }
            }
        }

    }

    public void createMasterCoderMari() {
        //Create MasterCoder Mari
        this.objects.add(PerkFactory.createMasterCoder(grid));
        this.objects.getLast().setCollisionDetector(collisionDetector);
        this.masterCoderCreated = true;
        this.masterCoderSound.play(true);

    }

    /**
     * Add a new element to the projectiles list
     *
     * @param projectile
     */
    public static void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public Player getPlayer() {
        return player;
    }


}

