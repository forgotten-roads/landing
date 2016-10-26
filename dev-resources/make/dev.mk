run:
	lein trampoline run

repl:
	lein trampoline repl

publish:
	lein deploy clojars

local:
	lein install

clean:
	rm -rf paho* temp logs
