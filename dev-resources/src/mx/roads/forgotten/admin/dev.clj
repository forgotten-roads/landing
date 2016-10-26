(ns mx.roads.forgotten.admin.dev
  (:require [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as repl]
            [clojure.walk :refer [macroexpand-all]]
            [com.stuartsierra.component :as component]
            [clojusc.twig :as logger]
            ;; api
            [mx.roads.forgotten.app-server.components :as components]
            [mx.roads.forgotten.app-server.core :as core]
            [mx.roads.forgotten.app-server.util :as util]))

(def state :stopped)
(def system nil)

(defn init []
  (logger/set-level! ['mx.roads.forgotten.app-server
                      'nginx] :info)
  (if (util/in? [:initialized :started :running] state)
    (log/error "System has aready been initialized.")
    (do
      (alter-var-root #'system
        (constantly (components/init #'core/app)))
      (alter-var-root #'state (fn [_] :initialized))))
  state)

(defn deinit []
  (if (util/in? [:started :running] state)
    (log/error "System is not stopped; please stop before deinitializing.")
    (do
      (alter-var-root #'system (fn [_] nil))
      (alter-var-root #'state (fn [_] :uninitialized))))
  state)

(defn start
  ([]
    (if (nil? system)
      (init))
    (if (util/in? [:started :running] state)
      (log/error "System has already been started.")
      (do
        (alter-var-root #'system component/start)
        (alter-var-root #'state (fn [_] :started))))
    state)
  ([component-key]
    (alter-var-root #'system
                    (constantly (components/start system component-key)))))

(defn stop
  ([]
    (if (= state :stopped)
      (log/error "System already stopped.")
      (do
        (alter-var-root #'system
          (fn [s] (when s (component/stop s))))
        (alter-var-root #'state (fn [_] :stopped))))
    state)
  ([component-key]
    (alter-var-root #'system
                    (constantly (components/stop system component-key)))))

(defn restart [component-key]
  (alter-var-root #'system
                  (constantly (components/restart system component-key))))

(defn run []
  (if (= state :running)
    (log/error "System is already running.")
    (do
      (if (not (util/in? [:initialized :started :running] state))
        (init))
      (if (not= state :started)
        (start))
      (alter-var-root #'state (fn [_] :running))))
  state)

(defn -refresh
  ([]
    (repl/refresh-all))
  ([& args]
    (apply #'repl/refresh-all args)))

(defn refresh [& args]
  "This is essentially an alias for clojure.tools.namespace.repl/refresh."
  (if (util/in? [:started :running] state)
    (stop))
  (apply -refresh args))

(defn reset []
  (stop)
  (deinit)
  (refresh :after 'mx.roads.forgotten.app-server.dev/run))

;;; Aliases

(def reload #'reset)
