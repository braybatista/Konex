defmodule ReactiveCommons.MixProject do
  use Mix.Project

  @version "0.0.1"

  def project do
    [
      app: :Hello_world_app,
      version: @version,
      deps: deps(),
    ]
  end

  defp hello(_) do
    Mix.shell().info("Hello world")
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      # {:dep_from_hexpm, "~> 0.3.0"},
      {:poison, "~> 5.0"},
      {:amqp, "~> 3.2"},
      {:uuid, "~> 1.1"},
      {:ex_doc, ">= 0.0.0", only: :dev, runtime: false},
      {:telemetry, "~> 1.2"},
      {:mock, "~> 0.3", only: :test}
      # {:dep_from_git, git: "https://github.com/elixir-lang/my_dep.git", tag: "0.1.0"}
    ]
  end
end