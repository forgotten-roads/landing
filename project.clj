(defproject mx.roads.forgotten/landing "0.0.1-SNAPSHOT"
  :description "The FRMX Landing Page"
  :url "https://github.com/forgotten-roads/landing"
  :scm {
    :name "git"
    :url  "https://github.com/forgotten-roads/landing"}
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
    ;; Database
    [com.taoensso/carmine "2.15.0"]
    ;; Messaging
    [clojurewerkz/machine_head "1.0.0-beta11-SNAPSHOT"]
    ;; Support & Util libs
    [clojusc/env-ini "0.1.0"]
    [clojusc/twig "0.3.0"]
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
        :project {:name "FRMX Landing Page"}
        :output-path "docs/master/current"
        :doc-paths ["docs/source"]
        :namespaces [#"^mx.roads.forgotten\."]
        :metadata {
          :doc/format :markdown
          :doc "Documentation forthcoming"}}}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11" :exclusions [org.clojure/clojure]]
        [lein-simpleton "1.3.0" :exclusions [org.clojure/clojure]]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns mx.roads.forgotten.landing.dev}
      :aot [mx.roads.forgotten.landing.server]
      :main mx.roads.forgotten.landing.server}})
