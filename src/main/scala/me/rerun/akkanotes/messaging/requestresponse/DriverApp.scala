package me.rerun.akkanotes.messaging.requestresponse

import akka.actor.ActorSystem
import akka.actor.Props
import me.rerun.akkanotes.messaging.protocols.StudentProtocol._

object DriverApp extends App{
  
  
  //Initialize the ActorSystem
  val system=ActorSystem("StudentTeacherSystem")
  val studentRef=system.actorOf(Props[StudentActor], "studentActor")
  studentRef!InitSignal
  
  Thread.sleep (2000) //Let's wait for a couple of seconds before we shut down the system
  system.shutdown()
  

}