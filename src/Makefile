JFLAGS =
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Server.java \
	Client.java \
	Registry.java \
	exceptionSys/CustomRemoteException.java \
	registry/RegistryRequestMessage.java \
	registry/RemoteInvocationHandler.java \
	registry/RemoteInvocationMessage.java \
	registry/RemoteObjectReference.java \
	registry/ReturnMessage.java \
	serverDatabases/DatabaseArchive.java \
	serverDatabases/DatabaseOfDatabases.java \
	serverDatabases/CapitalQueryInterface.java \
	serverDatabases/ExecutionAgent.java \
	serverDatabases/NationsDatabase.java \
	serverDatabases/ServerRMIHelper.java \
	serverDatabases/State.java \
	serverDatabases/StatesDatabase.java \
	util/Command.java \
	util/Config.java \
	util/RegistryMessenger.java \

RMCLASSES = \
	Server.java \
	Client.java \
	Registry.java \
	exceptionSys/CustomRemoteException.java \
	registry/RegistryRequestMessage.java \
	registry/RemoteInvocationHandler.java \
	registry/RemoteInvocationMessage.java \
	registry/RemoteObjectReference.java \
	registry/ReturnMessage.java \
	serverDatabases/DatabaseArchive.java \
	serverDatabases/DatabaseOfDatabases.java \
	serverDatabases/CapitalQueryInterface.java \
	serverDatabases/ExecutionAgent.java \
	serverDatabases/NationsDatabase.java \
	serverDatabases/ServerRMIHelper.java \
	serverDatabases/State.java \
	serverDatabases/StatesDatabase.java \
	util/Command.java \
	util/Config.java \
	util/RegistryMessenger.java \


default: classes

classes: $(CLASSES:.java=.class)

clean:	
	rm $(RMCLASSES)
	
