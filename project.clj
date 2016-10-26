(defproject mx.roads.forgotten/admin "0.0.1-SNAPSHOT"
  :description "The FRMX Authentication and Authorization Service"
  :url "https://gitlab.com/forgotten-roads/auth"
  :scm {
    :name "git"
    :url  "https://gitlab.com/forgotten-roads/auth"}
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    ;; Nginx
    [nginx-clojure "0.4.4"]
    [nginx-clojure/nginx-clojure-embed "0.4.4"]
    ;; HTTP
    [compojure "1.6.0-beta1"]
    [ring/ring-core "1.6.0-beta6"]
    [ring/ring-defaults "0.3.0-beta1"]
    [ring/ring-devel "1.6.0-beta6"]
    [clj-http "3.3.0"]
    ;; Authentication and Authorization
    [com.cemerick/friend "0.2.3" :exlusions [clj-http]]
    [friend-google-openid "0.2.1"]
    ;; Database
    [com.taoensso/carmine "2.15.0"]
    ;; Messaging
    [clojurewerkz/machine_head "1.0.0-beta11-SNAPSHOT"]
    ;; Support & Util libs
    [clojure-ini "0.0.2"]
    [clojusc/twig "0.2.6"]
    [com.github.jnr/jnr-posix "3.0.31"]
    [com.stuartsierra/component "0.3.1"]
    [potemkin "0.4.3"]]
  :profiles {
    :uber {
      :aot :all}
    :test {
      :plugins [
        [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
        [lein-kibit "0.1.2" :exclusions [org.clojure/clojure]]]
      :source-paths ["test"]
        :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}}
    :doc {
      :plugins [
        [lein-codox "0.10.1"]]
      :codox {
        :project {:name "FRMX App Server"}
        :output-path "docs/master/current"
        :doc-paths ["docs/source"]
        :namespaces [#"^mx.roads.forgotten.app-server\."]
        :metadata {
          :doc/format :markdown
          :doc "Documentation forthcoming"}}}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11" :exclusions [org.clojure/clojure]]
        [lein-simpleton "1.3.0" :exclusions [org.clojure/clojure]]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns mx.roads.forgotten.app-server.dev}
      :aot [mx.roads.forgotten.app-server.dev.server]
      :main mx.roads.forgotten.app-server.dev.server}})
