# Welcome to Moise-DSL!
## About
Moise-DSL is embedded domain-specifig language for formulating multi-agent organizations to use in [Moise project](http://moise.sourceforge.net/), a Java implementation of Moise+ model for specifying organizations in multi-agent systems. 

[moise-scalaxb](https://github.com/silasg/moise-scalaxb) is needed as a library for Moise-DSL, since it is needed  to write the XML-based _Organization Modelling Language (OML)_ expected by Moise.

Moise-DSL was written using Scala 2.8.1 and Netbeans 6.9 with Scala Plugin but you can build moise-scalaxb using other Scala versions and Ant, too.

## Contents of the project
* **bin-distribution**: Complete executable multi-agent system based on [JaCaMo](http://jacamo.sourceforge.net/) including Moise-DSL, a patched version of Moise, [Jason AgentSpeak Interpreter](http://jason.sourceforge.net/), [CArtAgO](http://cartago.sourceforge.net/) for agent's environment artefact management and sample data
* **doc**: My thesis documenting Moise-DSL (only in German, for table of contents see below)
* **example**: An example organizational structure formulated in OML as well as Moise-DSL
* **lib**: Binary libraries (currently only needed for testing, moise-scalaxb was integrated using a project reference in Netbeans)
* **moise-patches**: Patches for Moise needed to fix an bug in Moise 0.8 and to integrate Moise-DSL
* **nbproject**: Netbeans project files
* **src**: Scala source code of Moise-DSL
* **test**: Scala source code of Moise-DSL unit tests based on JUnit 4

## Documentation
### Remarks
Please contact me if you have any questions or need an English translation of parts of the document. I'll try to provide it. I've written Moise-DSL as part of my studies of Information Systems at University at Duisburg-Essen in Germany so it's available in German only. That's also the reason why there are some chapters dealing with basic stuff like multi-agent systems or domain-specific languages in general.

### TOC
1. This chapter provides goal and motivation (lower the semantic gap between model of multi-agent systems/ organizations and their implementation)
2. This chapter describes multi-agent systems and their theoretical background
3. This chapter describes multi-agent organizations and their theoretical background
4. This chapter describes classes and use cases of domain-specific languages
5. This chapter describes Moise-DSL's architecture as well as how it is integrated into Moise and why Scala was chosen for implementation
6. This chapter describes Moise-DSL's implementation
7. Conclusion

### References
Since often it's interesting to have look at the references of a paper or thesis until you read the hole stuff, here they are:

Kent Beck. *Test-driven development : by example*. Addison-Wesley,
Boston, MA, USA, 1st edition, 2003.

Tristan Behrens, Mehdi Dastani, Jürgen Dix, Michael Köster, and Peter
Novák. The multi-agent programming contest from 2005–2010. *Annals of
Mathematics and Artificial Intelligence*, 59:277–311, 2010. 

Rafael Bordini and Jomi Hübner. Bdi agent programming in agentspeak
using jason. In Francesca Toni and Paolo Torroni, editors,
*Computational Logic in Multi-Agent Systems*, volume 3900 of *Lecture
Notes in Computer Science*, pages 143–164. Springer Berlin / Heidelberg,
2006. 

Rafael H. Bordini, Ana L. C. Bazzan, Rafael de O. Jannone, Daniel M.
Basso, Rosa M. Vicari, and Victor R. Lesser. Agentspeak(xl): efficient
intention selection in bdi agents via decision-theoretic task
scheduling. In *Proceedings of the first international joint conference
on Autonomous agents and multiagent systems: part 3*, AAMAS ’02, pages
1294–1302, New York, NY, USA, 2002. ACM. 

Rafael H. Bordini, Michael Wooldridge, and Jomi Fred Hübner.
*Programming Multi-Agent Systems in AgentSpeak using Jason (Wiley Series
in Agent Technology)*. John Wiley & Sons, Chichester, UK, 1st edition, 2007.

Mehdi Dastani. 2apl: a practical agent programming language. *Autonomous
Agents and Multi-Agent Systems*, 16:214–248, 2008. 

Mehdi Dastani, Frank de Boer, Frank Dignum, and John jules Meyer.
Programming agent deliberation: An approach illustrated using the 3apl
language. In *In Proceedings of The Second Conference on Autonomous
Agents and Multi-agent Systems (AAMAS’03)*, pages 97–104. ACM Press,
2003.

Mehdi Dastani, Davide Grossi, John-Jules Meyer, and Nick Tinnemeier.
Normative multi-agent programs and their logics. In John-Jules Meyer and
Jan Broersen, editors, *Knowledge Representation for Agents and
Multi-Agent Systems*, volume 5605 of * Lecture Notes in Computer
Science*, pages 16–31. Springer Berlin / Heidelberg, 2009. 

Gilles Dubochet. On Embedding Domain-specific Languages with
User-friendly Syntax. In *Proceedings of the 1st Workshop on
Domain-Specific Program Development*, pages 19–22, 2006. 

Gilles Dubochet. Computer Code as a Medium for Human Communication: Are
Programming Languages Improving? In Chris Exton and Jim Buckley,
editors, *Proceedings of the 21st Working Conference on the Psychology
of Programmers Interest Group*, pages 174–187, Limerick, Ireland, 2009.
University of Limerick. 

Martin Fowler. A pedagogical framework for domain-specific languages.
*Software, IEEE*, 26(4):13 –14, Jul.-Aug. 2009. 

Martin Fowler. *Domain Specific Languages*. Addison-Wesley Professional,
Boston, MA, USA, 1st edition, 2010.

Debasish Ghosh. Dsl for the uninitiated. *Queue*, 9:10:10–10:21, Jun.
2011. 

Mahdi Hannoun, Olivier Boissier, Jaime Sichman, and Claudette Sayettat.
Moise: An organizational model for multi-agent systems. In Maria Monard
and Jaime Sichman, editors, *Advances in Artificial Intelligence*,
volume 1952 of *Lecture Notes in Computer Science*, pages 156–165.
Springer Berlin / Heidelberg, 2000. 

Atzmon Hen-Tov, David H. Lorenz, and Lior Schachter. An interpretive
domain specific language workbench. In *Proceeding of the 24th ACM
SIGPLAN conference companion on Object oriented programming systems
languages and applications*, OOPSLA ’09, pages 751–752, New York, NY,
USA, 2009. ACM. 

Christian Hofer, Klaus Ostermann, Tillmann Rendel, and Adriaan Moors.
Polymorphic embedding of dsls. In *Proceedings of the 7th international
conference on Generative programming and component engineering*, GPCE
’08, pages 137–148, New York, NY, USA, 2008. ACM. 

Paul Hudak. Building domain-specific embedded languages. *ACM Comput.
Surv.*, 28, Dec. 1996. 

Michael N. Huhns and Larry M. Stephens. *Multiagent systems: a modern
approach to distributed artificial intelligence*, chapter Multiagent
systems and societies of agents, pages 79–120. MIT Press, Cambridge, MA,
USA, 2nd edition, 2000.

Jomi Hübner, Olivier Boissier, and Rafael Bordini. From organisation
specification to normative programming in multi-agent organisations. In
Jürgen Dix, João Leite, Guido Governatori, and Wojtek Jamroga, editors,
*Computational Logic in Multi-Agent Systems*, volume 6245 of *Lecture
Notes in Computer Science*, pages 117–134. Springer Berlin / Heidelberg,
2010. 

Jomi Hübner, Olivier Boissier, and Rafael Bordini. A normative
organisation programming language for organisation management
infrastructures. In Julian Padget, Alexander Artikis, Wamberto
Vasconcelos, Kostas Stathis, Viviane da Silva, Eric Matson, and Axel
Polleres, editors, * Coordination, Organizations, Institutions and Norms
in Agent Systems V*, volume 6069 of *Lecture Notes in Computer Science*,
pages 114–129. Springer Berlin / Heidelberg, 2010. 

Jomi Hübner, Olivier Boissier, Rosine Kitio, and Alessandro Ricci.
Instrumenting multi-agent organisations with organisational artifacts
and agents. *Autonomous Agents and Multi-Agent Systems*, 20:369–400,
2010. 

Jomi Hübner, Jaime Sichman, and Olivier Boissier. A model for the
structural, functional, and deontic specification of organizations in
multiagent systems. In Guilherme Bittencourt and Geber Ramalho, editors,
*Advances in Artificial Intelligence*, volume 2507 of *Lecture Notes in
Computer Science*, pages 439–448. Springer Berlin / Heidelberg, 2002. 

Jomi Hübner, Jaime Sichman, and Olivier Boissier. Using the moise+ for a
cooperative framework of mas reorganisation. In Ana Bazzan and Sofiane
Labidi, editors, *Advances in Artificial Intelligence - SBIA 2004*,
volume 3171 of *Lecture Notes in Computer Science*, pages 481–517.
Springer Berlin / Heidelberg, 2004. 

Jomi F. Hübner, Jaime S. Sichman, and Olivier Boissier. Developing
organised multiagent systems using the moise+ model: programming issues
at the system and agent levels. *International Journal of Agent-Oriented
Software Engineering (IJAOSE)*, 1(3/4):370–395, 2007. 

Jomi Fred Hübner, Rafael H. Bordini, G. Pacianotto Gouveia, Ricardo H.
Pereira, Gauthier Picard, Michele Piunti, and Jaime S. Sichman. Using
jason, moise, and cartago to develop a team of cowboys. In Jürgen Dix,
Michael Fisher, and Peter Novak, editors, * Proceedings of 10th
International Workshop on Computational Logic in Multi-Agent Systems
(CLIMA 2009)*, pages 203–207, 2009.

Jomi Fred Hübner, Jaime Simão Sichman, and Olivier Boissier. Moise+:
towards a structural, functional, and deontic model for mas
organization. In *Proceedings of the first international joint
conference on Autonomous agents and multiagent systems: part 1*, AAMAS
’02, pages 501–502, New York, NY, USA, 2002. ACM. 

Jomi Fred Hübner, Jaime Simão Sichman, and Olivier Boissier. S-moise+: A
middleware for developing organised multi-agent systems. In *COIN I,
volume 3913 of LNAI*, pages 64–78. Springer, 2006.

International Organization for Standardization ISO and International
Electrotechnical Commission IEC. Extended backus–naur form: Iso/iec
14977: 1996 (e), 1996. Standard.

Rosine Kitio, Olivier Boissier, Jomi Hübner, and Alessandro Ricci.
Organisational artifacts and agents for open multi-agent organisations:
“giving the power back to the agents”. In Jaime Sichman, Julian Padget,
Sascha Ossowski, and Pablo Noriega, editors, *Coordination,
Organizations, Institutions, and Norms in Agent Systems III*, volume
4870 of *Lecture Notes in Computer Science*, pages 171–186. Springer
Berlin / Heidelberg, 2008. 

Robert C. Martin. *Clean Code: A handbook of agile software
craftsmanship*. Prentice Hall, Boston, MA, USA, 1st edition, 2009.

Marjan Mernik, Jan Heering, and Anthony M. Sloane. When and how to
develop domain-specific languages. *ACM Comput. Surv.*, 37:316–344, Dec.
2005. 

Adriaan Moors, Frank Piessens, and Martin Odersky. Parser combinators in
Scala. Technical Report CW491, Department of Computer Science,
K.U.Leuven, Leuven, BE, Feb. 2008.

Martin Odersky. The scala experiment - can we provide better language
support for component systems? In *Proc. ACM Symposium on Principles of
Programming Languages*, pages 166–167, 2006.

Martin Odersky, Philippe Altherr, Vincent Cremet, Iulian Dragos, Gilles
Dubochet, Burak Emir, Sean McDirmid, Stéphane Micheloud, Nikolay
Mihaylov, Michel Schinz, Lex Spoon, Erik Stenman, and Matthias Zenger.
An Overview of the Scala Programming Language. 2nd edition, Technical
report, École Polytechnique Fédérale de Lausanne (EPFL), Lausanne, CH,
2006.

Martin Odersky, Lex Spoon, and Bill Venners. *Programming in Scala*.
Artima Press, Mountain View, CA, USA, 2nd edition, 2011.

Martin Odersky and Matthias Zenger. Scalable component abstractions. In
*Proceedings of the 20th annual ACM SIGPLAN conference on
Object-oriented programming, systems, languages, and applications*,
OOPSLA ’05, pages 41–57, New York, NY, USA, 2005. ACM.

Andrea Omicini, Alessandro Ricci, and Mirko Viroli. Coordination
artifacts as first-class abstractions for mas engineering: State of the
research. In Alessandro Garcia, Ricardo Choren, Carlos Lucena, Paolo
Giorgini, Tom Holvoet, and Alexander Romanovsky, editors, *Software
Engineering for Multi-Agent Systems IV*, volume 3914 of *Lecture Notes
in Computer Science*, pages 71–90. Springer Berlin / Heidelberg, 2006. 

Andrea Omicini and Franco Zambonelli. Coordination for internet
application development. *Autonomous Agents and Multi-Agent Systems*,
2:251–269, 1999. 

Michele Piunti, Olivier Boissier, Jomi F Hubner, and Alessandro Ricci.
Embodied organizations: a unifying perspective in programming agents,
organizations and environments. In George VourosEditor Nicoletta
Fornara, editor, *11th International Workshop on Coordination
Organization Institutions and Norms in Agent Systems Coordination
Organization Institutions and Norms in Agent Systems*, pages 98–114.
Springer, 2010.

Anand Rao. Agentspeak(l): Bdi agents speak out in a logical computable
language. In Walter Van de Velde and John Perram, editors, *Agents
Breaking Away*, volume 1038 of *Lecture Notes in Computer Science*,
pages 42–55. Springer Berlin / Heidelberg, 1996. 

Anand S. Rao and Michael P. Georgeff. Modeling rational agents within a
bdi-architecture. In J. Allen, R. Fikes, and E. Sandewall, editors,
*Proceedings of the 2nd International Conference on Principles of
Knowledge Representation and Reasoning (KR’91)*, pages 473–484, 1991.

T. Reenskaug. Models-views-controllers. Technical report, Xerox PARC,
Palo Alto, CA, USA, 1979.

Alessandro Ricci, Mirko Viroli, and Andrea Omicini. Programming mas with
artifacts. In Rafael Bordini, Mehdi Dastani, Jürgen Dix, and Amal El
Fallah Seghrouchni, editors, *Programming Multi-Agent Systems*, volume
3862 of *Lecture Notes in Computer Science*, pages 206–221. Springer
Berlin / Heidelberg, 2006. 

Alessandro Ricci, Mirko Viroli, and Andrea Omicini. Cartago: A framework
for prototyping artifact-based environments in mas. In Danny Weyns, H.
Parunak, and Fabien Michel, editors, * Environments for Multi-Agent
Systems III*, volume 4389 of *Lecture Notes in Computer Science*, pages
67–86. Springer Berlin / Heidelberg, 2007. 

Alessandro Ricci, Mirko Viroli, and Andrea Omicini. The a&a programming
model and technology for developing agent environments in mas. In Mehdi
Dastani, Amal El Fallah Seghrouchni, Alessandro Ricci, and Michael
Winikoff, editors, *Programming Multi-Agent Systems*, volume 4908 of
*Lecture Notes in Computer Science*, pages 89–106. Springer Berlin /
Heidelberg, 2008.

Lukas Rytz and Martin Odersky. Named and default arguments for
polymorphic object-oriented languages: a discussion on the design
implemented in the scala language. In *Proceedings of the 2010 ACM
Symposium on Applied Computing*, SAC ’10, pages 2090–2095, New York, NY,
USA, 2010. ACM.

Yoav Shoham. Agent-oriented programming. *Artificial Intelligence*,
60(1):51–92, 1993. 

D. Wampler and A. Payne. *Programming Scala: Scalability = Functional
Programming + Objects*, chapter Domain-Specific Languages in Scala,
pages 217–246. O’Reilly Series. O’Reilly Media, Sebastopol, CA, USA, 1st edition, 2009.

Michael Wooldridge. *An Introduction to Multiagent Systems*, chapter
Intelligent Agents, pages 21–47. Wiley, Chichester, UK, 2nd
edition, 2009.

Michael Wooldridge and Nicholas R. Jennings. Intelligent agents: Theory
and practice. *Knowledge Engineering Review*, 10(2):115–152, 1995.
