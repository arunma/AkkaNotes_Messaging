package me.rerun.akkanotes.messaging.firenforget

import akka.actor.ActorSystem
import akka.actor.Props
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._


/**
 * Let's have the student as a simple runnable App now instead of an Actor for the first part.
 * 
 * There's a test class which actually does the same thing as this App
 * 
 */

object StudentApp extends App{
  
  val actorSystem=ActorSystem("StudentTeacherSystem")
  //val teacherActorRef=actorSystem.actorOf(Props[TeacherPrintActor])
  val teacherActorRef=actorSystem.actorOf(Props[TeacherLogActor])
  
  teacherActorRef!QuoteRequest
  
  actorSystem.shutdown()
  
}