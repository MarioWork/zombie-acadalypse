package org.academiadecodigo.altcatras65.util;

import org.academiadecodigo.altcatras65.game_objects.GameObject;
import org.academiadecodigo.altcatras65.game_objects.alive.enemy.Enemy;
import org.academiadecodigo.altcatras65.game_objects.alive.player.Player;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.Mouse;
import org.academiadecodigo.altcatras65.game_objects.props.projectile.Projectile;
import org.academiadecodigo.altcatras65.grid.gridposition.GridDirectionType;

import java.util.LinkedList;


public class CollisionDetector {

    private LinkedList<GameObject> gameObjects;


    public CollisionDetector(LinkedList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Checks for collisions with specific gameObject
     * Requires iterating the array once
     *
     * @param directionType
     * @param paramObject
     */
    public void check(GridDirectionType directionType, GameObject paramObject) {

        for (GameObject object : gameObjects) {

            //Not check with itself
            if (paramObject == object) {
                continue;
            }

            //If the next position of the object is same as the other object
            if (paramObject.getPos().compareNextPosition(directionType, object.getPos())) {

                //If paramObject is a player
                if (paramObject instanceof Player) {
                    if (object instanceof Enemy) {
                        Player player = (Player) paramObject;
                        player.hit(((Enemy) object).getType().getDamage());
                    }

                    if (object instanceof Projectile) {
                        //do nothing
                    }
                }

            }
        }
    }

    /**
     * Check for if it will collide or not
     *
     * @param direction
     * @param paramObject
     * @return
     */
    public boolean isSafe(GridDirectionType direction, GameObject paramObject) {

        for (GameObject obj : gameObjects) {


            // No point in checking collisions with self
            if (paramObject == obj) {
                continue;
            }
            //if objects are player or enemy, they aren't safe if next pos of obj is type wall
            if (paramObject.getPos().compareNextPosition(direction, obj.getPos())) {
                //if any object chose next pos player, and object isn't enemy, he is safe
                if (obj instanceof Player) {

                    //if enemy choose next pos player, player takes damage
                    if (paramObject instanceof Enemy) {
                        //do nothing
                    }
                }


                //if any object choose next pos enmy, he isn't safe, including projectile
                if (obj instanceof Enemy) {
                    //if player choose next pos enemy, player takes damage
                    if (paramObject instanceof Player) {
                        ((Player) paramObject).hit(((Enemy) obj).getType().getDamage());
                    }
                    return false;
                }
                return false;
            }
            /*if (object instanceof Enemy && obj instanceof Projectile) {
                return false;
            }*/
        }

        return true;
    }

}