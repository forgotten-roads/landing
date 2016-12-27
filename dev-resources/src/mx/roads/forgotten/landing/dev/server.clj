(ns mx.roads.forgotten.landing.dev.server
  (:require [clojure.tools.logging :as log]
            [clojusc.twig :as logger]
            [com.stuartsierra.component :as component]
            [mx.roads.forgotten.landing.components :as components]
            [mx.roads.forgotten.landing.core :as core]
            [mx.roads.forgotten.landing.util :as util])
  (:gen-class))

(defn -main
  ""
  [& args]
  ;; Set the initial log-level before the components set the log-levels for
  ;; the configured namespaces
  (logger/set-level! ['mx.roads.forgotten.app-server 'nginx] :info)
  (let [system (components/init #'core/app)
        local-ip (util/get-default-ip)]
    (log/info "FRMX landing page server local IP address:" local-ip)
    (component/start system)
    (util/add-shutdown-handler #(component/stop system))
    ;; Since there's no daemon, keep things running with a Thread join
    ;(util/join-thread)
    ))
