defmodule ReactiveCommons.MixProject do
  use Mix.Project

  @version "1.0.0"

  def project do
    [
      app: :Hello_world_app,
      version: @version,
      deps: deps(),
      description: description(),
      package: package(),
      source_url: "https://github.com/braybatista/Konex"
    ]
  end

  defp hello(_) do
    Mix.shell().info("Hello world")
  end

  defp description() do
    "Domain driven async abstractions like Domain Event Bus, Event subscriptions/emit, Async Command handling and Async Req/Reply."
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
      # {:dep_from_git, git: "https://github.com/braybatista/Konex.git", tag: "1.0.0"}
    ]
  end

  defp package() do
    [
      # This option is only needed when you don't want to use the OTP application name
      name: "Konex",
      files: ["test", "mix.exs", ".formatter.exs", "mix.lock"],
      maintainers: ["Brayan Batista Zuniga"],
      licenses: ["Apache-2.0"],
      links: %{"GitHub" => "https://github.com/braybatista/Konex"}
    ]
  end
end