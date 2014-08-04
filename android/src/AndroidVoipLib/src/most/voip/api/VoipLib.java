/*
 * Project MOST - Moving Outcomes to Standard Telemedicine Practice
 * http://most.crs4.it/
 *
 * Copyright 2014, CRS4 srl. (http://www.crs4.it/)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * See license-GPLv2.txt or license-MIT.txt
 */

package most.voip.api;

import java.util.HashMap;
import most.voip.api.interfaces.IAccount;
import most.voip.api.interfaces.IBuddy;
import most.voip.api.interfaces.ICall;
import most.voip.api.interfaces.IServer;

import android.content.Context;
import android.os.Handler;

/**
 * It is the core class of the Library, that allows you to:
 * <ul>
 * <li>initialize the Voip Library</li>
 * <li>create an account and register it on a remote Sip Server</li>
 * <li>make a call</li>
 * <li>listen for incoming calls and answer</li>
 * <li>get instances of IAccount, ICall and IServer objects</li>
 * </ul>
 *
 */
public interface VoipLib {
	
	
	/**
	 * Initialize the Voip Lib
	 * @param context application context of the activity that uses this library
	 * @param configParams All needed configuration string parameters. All the supported parameters are the following:
	 * 	<ul>
	 * 		<li>sipServerIp: the ip address of the Sip Server (e.g Asterisk)</li>
	 * 		<li>userName: the account name of the peer to register to the sip server </li>
	 * 		<li>userPwd: the account password of the peer to register to the sip server </li>
	 * 		<li>sipServerPort: the port of the sip server (default:"5060") </li>
	 * 	</ul>
	 * 
	 * @param notificationHandler the handller that will receive all sip notifications 
	 * @return true if the initialization request completes without errors, false otherwise 
	 */
	public boolean initLib(Context context, HashMap<String,String> configParams, Handler notificationHandler);
	
	/**
	 * Destroy the Voip Lib
	 * @return <code>true</code> if no error occurred in the deinitialization process
	 */
	public boolean destroyLib();
   
    /**
     * Register the account according to the configuration params provided in the {@link #initLib(HashMap, Handler)} method
     * @return <code>true</code> if the registration request was sent to the sip server, <code>false</code> otherwise
     */
	public boolean registerAccount();
	
	/**
	 * Unregister the currently registered account [Not Implemented yet]
	 * @return <code>true</code> if the unregistration request was sent to the sip server, <code>false</code> otherwise
	 */
	public boolean unregisterAccount();
       
    /**
     * Make a call to the specific extension
     * 
     * @param extension The extension to dial
     * @return true if no error occurred during this operation, false otherwise
     */
    public boolean makeCall(String extension);
    
    /**
     * Answer a call
     * @return false if this command was ignored for some reasons (e.g there is already an active call), true otherwise
     */
    public boolean answerCall();
    
    /**
     * Put the active call on hold status
     * @return true if no error occurred during this operation, false otherwise
     */
    public boolean holdCall();
    
    /**
     *  Put the active call on active status
     *  @return true if no error occurred during this operation, false otherwise
     */
    public boolean unholdCall();
    
    /**
     * Close the current active call
     * @return true if no error occurred during this operation, false otherwise
     */
    public boolean hangupCall();
     
    
    /**
     * Get informations about the local sip account
     * @return  informations about the local sip account , like its current state
     */
    public IAccount getAccount();
    
    /**
     * Get the current call info (if any)
     * @return informations about the current call (if any), like the current Call State
     */
    public ICall getCall();
    
    /**
     *  Get informations about the remote Sip Server
     * @return informations about the current sip server, like the current Server State
     */
    public IServer getServer();
    
}
