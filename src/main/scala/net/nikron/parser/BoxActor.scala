package net.nikron
package hipactors

import akka.actor.Actor
import com.imadethatcow.hipchat.common.caseclass.WebhookRoomMessage
import scala.util.Random

class BoxActor extends Actor {
  val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  var active = false

  def receive = {
    case message: WebhookRoomMessage =>
      if (message.item.message.message.toLowerCase() == "!shutthebox") {
        val rollValue = roll()
        active = true
        context.actorSelection("/user/drunkestbot") ! new DrunkSpeak("Starting " +
          "a new game of Shut The Box!")
        context.actorSelection("/user/drunkestbot") ! new DrunkSpeak("Your " + 
          "available numbers are " + numbers.mkstring(", ") + ". You just " + 
          "rolled a " + rollValue.toString() + ".")
      }
  }

  def roll() : Int = {
    return (2 + Random.nextInt(11))
  }

}
