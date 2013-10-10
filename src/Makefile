JFLAGS =
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	CapitalsServer.java \
	Client.java \
	Registry.java \
	exceptionSys/CustomRemoteException.java \
	exceptionSys/CapitalNotFoundException.java \
	registry/RegistryRequestMessage.java \
	registry/RemoteInvocationHandler.java \
	registry/RemoteInvocationMessage.java \
	registry/RemoteObjectReference.java \
	registry/ReturnMessage.java \
	serverDatabases/CapitalQueryInterface.java \
	serverDatabases/DatabaseArchive.java \
	serverDatabases/ExecutionAgent.java \
	serverDatabases/NationsDatabase.java \
	serverDatabases/ServerRMIHelper.java \
	serverDatabases/State.java \
	serverDatabases/StatesDatabase.java \
	serverDatabases/DatabaseOfDatabases.java \
	util/Command.java \
	util/Config.java \
	util/RegistryMessenger.java \

RMCLASSES = \
	CapitalsServer.java \
	Client.java \
	Registry.java \
	exceptionSys/CustomRemoteException.java \
	exceptionSys/CapitalNotFoundException.java \
	registry/RegistryRequestMessage.java \
	registry/RemoteInvocationHandler.java \
	registry/RemoteInvocationMessage.java \
	registry/RemoteObjectReference.java \
	registry/ReturnMessage.java \
	serverDatabases/CapitalQueryInterface.java \
	serverDatabases/DatabaseArchive.java \
	serverDatabases/ExecutionAgent.java \
	serverDatabases/NationsDatabase.java \
	serverDatabases/ServerRMIHelper.java \
	serverDatabases/State.java \
	serverDatabases/StatesDatabase.java \
	serverDatabases/DatabaseOfDatabases.java \
	util/Command.java \
	util/Config.java \
	util/RegistryMessenger.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:	
	rm $(RMCLASSES)
	
